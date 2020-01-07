package com.alex44.digitalnomadstestapp.model.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class NewsResponseDTO(
    @Expose @SerializedName("status") val status : String?,
    @Expose @SerializedName("totalResults") val total : Int?,
    @Expose @SerializedName("articles") val articles : List<NewsArticleDTO>?
)