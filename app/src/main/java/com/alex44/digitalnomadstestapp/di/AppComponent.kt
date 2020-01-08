package com.alex44.digitalnomadstestapp.di

import com.alex44.digitalnomadstestapp.di.modules.AppModule
import com.alex44.digitalnomadstestapp.di.modules.CiceroneModule
import com.alex44.digitalnomadstestapp.di.modules.ImageModule
import com.alex44.digitalnomadstestapp.di.modules.RepoModule
import com.alex44.digitalnomadstestapp.presenter.MainPresenter
import com.alex44.digitalnomadstestapp.presenter.NewsPresenter
import com.alex44.digitalnomadstestapp.ui.activities.MainActivity
import com.alex44.digitalnomadstestapp.ui.adapters.NewsRVAdapter
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, CiceroneModule::class, RepoModule::class, ImageModule::class])
interface AppComponent {

    fun inject(mainPresenter: MainPresenter)
    fun inject(mainActivity: MainActivity)
    fun inject(newsPresenter: NewsPresenter)
    fun inject(adapter: NewsRVAdapter)

}