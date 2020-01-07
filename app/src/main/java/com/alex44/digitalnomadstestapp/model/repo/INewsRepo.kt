package com.alex44.digitalnomadstestapp.model.repo

import com.alex44.digitalnomadstestapp.model.dto.NewsResponseDTO
import io.reactivex.Maybe

interface INewsRepo {

    fun getNews(pageNum : Int) : Maybe<NewsResponseDTO>

}