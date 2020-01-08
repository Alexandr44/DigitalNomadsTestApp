package com.alex44.digitalnomadstestapp.presenter

import com.alex44.digitalnomadstestapp.model.dto.NewsArticleDTO
import com.alex44.digitalnomadstestapp.model.repo.INewsRepo
import com.alex44.digitalnomadstestapp.ui.adapters.NewsRVAdapter
import com.alex44.digitalnomadstestapp.view.NewsView
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import io.reactivex.Scheduler
import io.reactivex.disposables.Disposable
import timber.log.Timber
import javax.inject.Inject

@InjectViewState
class NewsPresenter(private val mainThreadScheduler : Scheduler) : MvpPresenter<NewsView>() {

    companion object {
        const val MAX_PAGE = 5
    }

    var curPage = 1

    @Inject
    lateinit var repo : INewsRepo

    var disposable : Disposable? = null

    var data : MutableList<NewsArticleDTO> = ArrayList()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        init()
        loadData()
    }

    private fun init() {
        viewState.initRV()
    }

    private fun loadData() {
        disposable = repo.getNews(curPage)
            .observeOn(mainThreadScheduler)
            .subscribe({response ->
                response.articles?.let {
                    data.addAll(it)
                    viewState.updateRV()
                }
            },
            {t ->
                t.message?.let {
                    viewState.showMessage(it)
                    Timber.e(it)
                } ?: run{
                    viewState.showMessage("ERROR")
                }
            })
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable?.let {
            if (!it.isDisposed) it.dispose()
        }
    }

    fun bind(holder: NewsRVAdapter.ViewHolder) {
        with(data.get(holder.getPos())) {
            holder.setTitle(title?:"")
            holder.setDesc(description?:"")
            holder.setDate(publishedAt?:"")
            holder.setImage(imageUrl?:"")
        }
    }

    fun loadMore() {
        if (curPage < MAX_PAGE) {
            if (disposable?.isDisposed!!) {
                curPage++
                loadData()
            }
        }
    }

}