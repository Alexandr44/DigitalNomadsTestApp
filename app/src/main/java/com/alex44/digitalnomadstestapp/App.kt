package com.alex44.digitalnomadstestapp

import android.app.Application
import com.alex44.digitalnomadstestapp.di.AppComponent
import com.alex44.digitalnomadstestapp.di.DaggerAppComponent
import com.alex44.digitalnomadstestapp.di.modules.AppModule
import com.github.ajalt.timberkt.Timber

class App : Application() {

    companion object{
        lateinit var instance : App
    }

    lateinit var appComponent : AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this
        Timber.plant(Timber.DebugTree())
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }
}