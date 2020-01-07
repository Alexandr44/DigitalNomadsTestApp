package com.alex44.digitalnomadstestapp.model.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class NewsArticleDTO(
    @Expose @SerializedName("source") val source : NewsSourceDTO?,
    @Expose @SerializedName("author") val author : String?,
    @Expose @SerializedName("title") val title : String?,
    @Expose @SerializedName("description") val description : String?,
    @Expose @SerializedName("url") val newsUrl : String?,
    @Expose @SerializedName("urlToImage") val imageUrl : String?,
    @Expose @SerializedName("publishedAt") val publishedAt : String?,
    @Expose @SerializedName("content") val content : String?
)