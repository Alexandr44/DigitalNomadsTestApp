package com.alex44.digitalnomadstestapp.di.modules

import android.widget.ImageView
import com.alex44.digitalnomadstestapp.common.interfaces.IImageLoader
import com.alex44.digitalnomadstestapp.common.ui.GlideImageLoader
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class ImageModule {

    @Named("Glide")
    @Provides
    fun imageLoader() : IImageLoader<ImageView> = GlideImageLoader()

}