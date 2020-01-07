package com.alex44.digitalnomadstestapp.model.api

import com.alex44.digitalnomadstestapp.common.model.api.ApiStrings.Companion.API_DATE_FROM
import com.alex44.digitalnomadstestapp.common.model.api.ApiStrings.Companion.API_KEY
import com.alex44.digitalnomadstestapp.common.model.api.ApiStrings.Companion.API_MODE
import com.alex44.digitalnomadstestapp.common.model.api.ApiStrings.Companion.API_SORT_BY
import com.alex44.digitalnomadstestapp.common.model.api.ApiStrings.Companion.NEWS_URL
import com.alex44.digitalnomadstestapp.model.dto.NewsResponseDTO
import io.reactivex.Maybe
import retrofit2.http.GET
import retrofit2.http.Query

interface INewsSource {

    @GET("$NEWS_URL?q=$API_MODE&from=$API_DATE_FROM&sortBy=$API_SORT_BY&apiKey=$API_KEY")
    fun getNews(@Query("page") pageNum : Int) : Maybe<NewsResponseDTO>

}