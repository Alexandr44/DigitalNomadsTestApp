package com.alex44.digitalnomadstestapp.di.modules

import com.alex44.digitalnomadstestapp.model.api.INewsSource
import com.alex44.digitalnomadstestapp.model.repo.INewsRepo
import com.alex44.digitalnomadstestapp.model.repo.INewsRepoCache
import com.alex44.digitalnomadstestapp.model.repo.NewsRepo
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module(includes = [ApiModule::class, CacheModule::class])
class RepoModule {

    @Provides
    fun newsRepo(newsSource : INewsSource, @Named("Room") repoCache : INewsRepoCache) : INewsRepo = NewsRepo(newsSource, repoCache)

}