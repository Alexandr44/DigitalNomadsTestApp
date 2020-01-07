package com.alex44.digitalnomadstestapp.di.modules

import com.alex44.digitalnomadstestapp.model.api.INewsSource
import com.alex44.digitalnomadstestapp.model.repo.INewsRepo
import com.alex44.digitalnomadstestapp.model.repo.NewsRepo
import dagger.Module
import dagger.Provides

@Module(includes = [ApiModule::class])
class RepoModule {

    @Provides
    fun newsRepo(newsSource : INewsSource) : INewsRepo = NewsRepo(newsSource)

}