package com.alex44.digitalnomadstestapp.model.room.dao

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.alex44.digitalnomadstestapp.model.room.NewsArticleRoom

@Dao
interface NewsArticleDao {

    @Insert(onConflict = REPLACE)
    fun insert(newsArticle: NewsArticleRoom)

    @Insert(onConflict = REPLACE)
    fun insert(vararg newsArticles: NewsArticleRoom)

    @Insert(onConflict = REPLACE)
    fun insert(newsArticleList: List<NewsArticleRoom>)

    @Update
    fun update(newsArticle: NewsArticleRoom)

    @Update
    fun update(vararg newsArticles: NewsArticleRoom)

    @Update
    fun update(newsArticleList: List<NewsArticleRoom>)

    @Delete
    fun delete(newsArticle: NewsArticleRoom)

    @Delete
    fun delete(vararg newsArticles: NewsArticleRoom)

    @Delete
    fun delete(newsArticleList: List<NewsArticleRoom>)

    @Query("SELECT * FROM NewsArticleRoom n ORDER BY n.publishedAt desc LIMIT :count")
    fun findLast(count : Int) : List<NewsArticleRoom>

}