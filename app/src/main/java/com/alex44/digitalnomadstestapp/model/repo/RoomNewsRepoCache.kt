package com.alex44.digitalnomadstestapp.model.repo

import com.alex44.digitalnomadstestapp.common.model.db.DatabaseRoom
import com.alex44.digitalnomadstestapp.model.dto.NewsArticleDTO
import com.alex44.digitalnomadstestapp.model.room.NewsArticleRoom

class RoomNewsRepoCache : INewsRepoCache {

    override fun putNewsArticles(newsList: List<NewsArticleDTO>) {
        val roomList : MutableList<NewsArticleRoom> = ArrayList()
        newsList.forEach { dto ->
            with(dto) {
                val room = NewsArticleRoom(
                    author,
                    title,
                    description,
                    newsUrl,
                    imageUrl,
                    publishedAt
                )
                roomList.add(room)
            }
        }
        DatabaseRoom.getInstance().getNewsArticleDao().insert(roomList)
    }

    override fun getNewsArticles(count : Int): List<NewsArticleDTO> {
        val roomList = DatabaseRoom.getInstance().getNewsArticleDao().findLast(count)
        val dtoList : MutableList<NewsArticleDTO> = ArrayList()
        roomList.forEach {room ->
            with(room) {
                val dto = NewsArticleDTO(null, author, title, description, newsUrl,
                    imageUrl, publishedAt, null)
                dtoList.add(dto)
            }
        }
        return dtoList
    }

}