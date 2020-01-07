package com.alex44.digitalnomadstestapp.model.repo

import com.alex44.digitalnomadstestapp.model.api.INewsSource
import com.alex44.digitalnomadstestapp.model.dto.NewsResponseDTO
import io.reactivex.Maybe
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class NewsRepo(private var source: INewsSource) : INewsRepo {

    override fun getNews(pageNum: Int): Maybe<NewsResponseDTO> {
        return source.getNews(pageNum)
            .subscribeOn(Schedulers.io())
            .map { t ->
                Timber.d("Total:"+t.total)
                return@map t
            }
    }
}