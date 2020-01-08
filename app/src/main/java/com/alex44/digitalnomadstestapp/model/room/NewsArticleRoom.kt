package com.alex44.digitalnomadstestapp.model.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NewsArticleRoom(
    val author : String?,
    val title : String?,
    val description : String?,
    @PrimaryKey val newsUrl : String,
    val imageUrl : String?,
    val publishedAt : String?
)