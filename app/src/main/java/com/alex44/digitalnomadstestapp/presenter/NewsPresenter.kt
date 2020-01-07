package com.alex44.digitalnomadstestapp.presenter

import com.alex44.digitalnomadstestapp.model.repo.INewsRepo
import com.alex44.digitalnomadstestapp.view.NewsView
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import io.reactivex.Scheduler
import io.reactivex.disposables.Disposable
import timber.log.Timber
import javax.inject.Inject

@InjectViewState
class NewsPresenter(private val mainThreadScheduler : Scheduler) : MvpPresenter<NewsView>() {

    @Inject
    lateinit var repo : INewsRepo

    var disposable : Disposable? = null

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        init()
        loadData()
    }

    private fun init() {
        viewState.initRV()
    }

    private fun loadData() {
        disposable = repo.getNews(1)
            .observeOn(mainThreadScheduler)
            .subscribe({response ->
                Timber.d("Message: "+response.status)
                viewState.showMessage("DONE:"+response.status)
            },
                {t ->
                    t.message?.let {
                        viewState.showMessage(it)
                        Timber.d(it)
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

}