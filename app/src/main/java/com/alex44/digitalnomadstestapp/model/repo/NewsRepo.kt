package com.alex44.digitalnomadstestapp.model.repo

import com.alex44.digitalnomadstestapp.common.interfaces.INetworkStatus
import com.alex44.digitalnomadstestapp.common.model.exception.OfflineException
import com.alex44.digitalnomadstestapp.model.api.INewsSource
import com.alex44.digitalnomadstestapp.model.dto.NewsResponseDTO
import com.alex44.digitalnomadstestapp.model.enums.NewsType
import io.reactivex.Maybe
import io.reactivex.MaybeEmitter
import io.reactivex.schedulers.Schedulers

class NewsRepo(private var source: INewsSource, private val cache: INewsRepoCache, private val networkStatus: INetworkStatus) : INewsRepo {

    override fun getNews(pageNum: Int): Maybe<NewsResponseDTO> {
        if (networkStatus.isOnline()) {
            return source.getNews(pageNum)
                .subscribeOn(Schedulers.io())
                .map { t ->
                    t.articles?.let {
                        cache.putNewsArticles(it.filter {article ->
                            article.newsType == NewsType.NEWS
                        })
                    }
                    return@map t
                }
        }
        else {
            return Maybe.create { emitter: MaybeEmitter<NewsResponseDTO> ->
                emitter.onError(OfflineException("No internet connection"))
            }
                .subscribeOn(Schedulers.io())
        }
    }
}