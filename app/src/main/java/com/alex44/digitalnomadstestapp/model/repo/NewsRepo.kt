package com.alex44.digitalnomadstestapp.model.repo

import com.alex44.digitalnomadstestapp.model.api.INewsSource
import com.alex44.digitalnomadstestapp.model.dto.NewsResponseDTO
import io.reactivex.Maybe
import io.reactivex.schedulers.Schedulers

class NewsRepo(private var source: INewsSource, private val cache: INewsRepoCache) : INewsRepo {

    override fun getNews(pageNum: Int): Maybe<NewsResponseDTO> {
        return source.getNews(pageNum)
            .subscribeOn(Schedulers.io())
            .map { t ->
                t.articles?.let {
                    cache.putNewsArticles(it)
                }
                return@map t
            }
    }
}