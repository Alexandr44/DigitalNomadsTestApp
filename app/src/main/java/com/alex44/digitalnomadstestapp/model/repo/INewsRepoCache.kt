package com.alex44.digitalnomadstestapp.model.repo

import com.alex44.digitalnomadstestapp.model.dto.NewsArticleDTO

interface INewsRepoCache {

    fun putNewsArticles(newsList : List<NewsArticleDTO>)

    fun getNewsArticles(count : Int) : List<NewsArticleDTO>

}