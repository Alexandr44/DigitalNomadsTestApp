package com.alex44.digitalnomadstestapp.di.modules

import com.alex44.digitalnomadstestapp.App
import dagger.Module
import dagger.Provides

@Module
class AppModule(val app : App) {

    @Provides
    fun app() = this.app

}