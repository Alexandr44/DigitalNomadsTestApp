package com.alex44.digitalnomadstestapp.view

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

@StateStrategyType(value = AddToEndSingleStrategy::class)
interface UrlView : MvpView {

    fun openUrl(url : String)

    fun showMessage(message: String)

    fun backToNews()

}