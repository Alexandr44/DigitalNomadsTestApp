package com.alex44.digitalnomadstestapp.di.modules

import com.alex44.digitalnomadstestapp.App
import dagger.Module
import dagger.Provides

@Module
class AppModule(app : App) {

    lateinit var app : App

    @Provides
    fun app() = this.app

}