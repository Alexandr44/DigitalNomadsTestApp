package com.alex44.digitalnomadstestapp.di

import com.alex44.digitalnomadstestapp.di.modules.AppModule
import com.alex44.digitalnomadstestapp.di.modules.CiceroneModule
import com.alex44.digitalnomadstestapp.presenter.MainPresenter
import com.alex44.digitalnomadstestapp.ui.activities.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, CiceroneModule::class])
interface AppComponent {

    fun inject(mainPresenter: MainPresenter)
    fun inject(mainPresenter: MainActivity)

}