package com.alex44.digitalnomadstestapp.presenter

import com.alex44.digitalnomadstestapp.view.UrlView
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter

@InjectViewState
class UrlPresenter(private val url : String) : MvpPresenter<UrlView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.openUrl(url)
    }

    fun backClicked() {
        viewState.backToNews()
    }

}