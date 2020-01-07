package com.alex44.digitalnomadstestapp.presenter

import com.alex44.digitalnomadstestapp.view.MainView
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter

@InjectViewState
class MainPresenter : MvpPresenter<MainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
    }

    fun init() {
        viewState.goToNews()
    }
}