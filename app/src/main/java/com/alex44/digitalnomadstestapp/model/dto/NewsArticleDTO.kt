package com.alex44.digitalnomadstestapp.model.dto

import com.alex44.digitalnomadstestapp.model.enums.NewsType
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class NewsArticleDTO(
    @Expose @SerializedName("source") val source : NewsSourceDTO? = null,
    @Expose @SerializedName("author") val author : String? = null,
    @Expose @SerializedName("title") val title : String? = null,
    @Expose @SerializedName("description") val description : String? = null,
    @Expose @SerializedName("url") val newsUrl : String = "",
    @Expose @SerializedName("urlToImage") val imageUrl : String? = null,
    @Expose @SerializedName("publishedAt") val publishedAt : String? = null,
    @Expose @SerializedName("content") val content : String? = null,
    var newsType: NewsType = NewsType.NEWS
)