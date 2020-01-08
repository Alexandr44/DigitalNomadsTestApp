package com.alex44.digitalnomadstestapp.di.modules

import com.alex44.digitalnomadstestapp.model.repo.INewsRepoCache
import com.alex44.digitalnomadstestapp.model.repo.RoomNewsRepoCache
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class CacheModule {

    @Named("Room")
    @Provides
    fun roomCache() : INewsRepoCache = RoomNewsRepoCache()

}