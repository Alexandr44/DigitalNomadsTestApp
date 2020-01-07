package com.alex44.digitalnomadstestapp.di.modules

import com.alex44.digitalnomadstestapp.common.interfaces.INetworkStatus
import com.alex44.digitalnomadstestapp.common.ui.NetworkStatus
import dagger.Module
import dagger.Provides

@Module
class NetworkModule {

    @Provides
    fun networkStatus() : INetworkStatus = NetworkStatus()

}