package com.alex44.digitalnomadstestapp.view

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

@StateStrategyType(value = AddToEndSingleStrategy::class)
interface NewsView : MvpView {

    fun showMessage(message: String)

    fun initRV()

    fun updateRV()

    fun goToUrl(newsUrl: String)

}