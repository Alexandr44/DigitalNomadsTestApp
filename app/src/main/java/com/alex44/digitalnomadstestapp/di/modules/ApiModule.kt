package com.alex44.digitalnomadstestapp.di.modules

import com.alex44.digitalnomadstestapp.App
import com.alex44.digitalnomadstestapp.common.model.api.ApiStrings.Companion.RETROFIT_BASE_URL
import com.alex44.digitalnomadstestapp.model.api.INewsSource
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.readystatesoftware.chuck.ChuckInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module(includes = [AppModule::class])
class ApiModule {

    @Named("baseUrl")
    @Provides
    fun baseUrl() = RETROFIT_BASE_URL

    @Singleton
    @Provides
    fun gson() = GsonBuilder()
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .excludeFieldsWithoutExposeAnnotation()
        .create()

    @Provides
    fun okHttpClient(chuckInterceptor : ChuckInterceptor, httpLoggingInterceptor : HttpLoggingInterceptor) =
        OkHttpClient.Builder()
            .addNetworkInterceptor(httpLoggingInterceptor)
            .addNetworkInterceptor(chuckInterceptor)
            .build()

    @Provides
    fun chuckInterceptor(app : App) = ChuckInterceptor(app.applicationContext)

    @Provides
    fun httpLoggingInterceptor() = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    @Provides
    fun retrofit(@Named("baseUrl") baseUrl : String, gson : Gson, okHttpClient : OkHttpClient) : Retrofit =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()

    @Provides
    fun newsSource(retrofit : Retrofit) : INewsSource = retrofit.create(INewsSource::class.java)

}