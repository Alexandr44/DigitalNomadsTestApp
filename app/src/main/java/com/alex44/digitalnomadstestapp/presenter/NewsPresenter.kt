package com.alex44.digitalnomadstestapp.presenter

import com.alex44.digitalnomadstestapp.common.model.exception.OfflineException
import com.alex44.digitalnomadstestapp.model.dto.NewsArticleDTO
import com.alex44.digitalnomadstestapp.model.enums.NewsType
import com.alex44.digitalnomadstestapp.model.repo.INewsRepo
import com.alex44.digitalnomadstestapp.view.NewsRvItemErrorView
import com.alex44.digitalnomadstestapp.view.NewsRvItemView
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

    var loadError = false

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
                    curPage++
                }
            },
            {t ->
                t.message?.let {
                    viewState.showMessage(it)
                    Timber.e(it)
                } ?: run{
                    viewState.showMessage("ERROR")
                }
                if (t is OfflineException) {
                    loadError = true
                    val info = NewsArticleDTO(title = t.message, newsType = NewsType.ERROR)
                    data.add(info)
                    viewState.updateRV()
                }
            })
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable?.let {
            if (!it.isDisposed) it.dispose()
        }
    }

    fun bind(holder: NewsRvItemView) {
        with(data[holder.getPos()!!]) {
            holder.setTitle(title?:"")
            holder.setDesc(description?:"")
            holder.setDate(publishedAt?:"")
            holder.setImage(imageUrl?:"")
        }
    }

    fun bind(holder: NewsRvItemErrorView) {
        holder.setMessage(data.find {article ->
            article.newsType == NewsType.ERROR
        }?.title?:"")
    }

    fun loadMore() {
        if (loadError) return
        if (curPage > MAX_PAGE) return
        if (!disposable?.isDisposed!!) return
        loadData()
    }

    fun clicked(position: Int) {
        if (position > data.size) return
        val item = data[position]
        viewState.goToUrl(item.newsUrl)
    }

    fun retry() {
        loadError = false
        data.removeAll { it.newsType != NewsType.NEWS }
        loadMore()
    }

}