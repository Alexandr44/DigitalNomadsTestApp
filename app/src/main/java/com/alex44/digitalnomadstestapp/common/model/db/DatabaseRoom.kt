package com.alex44.digitalnomadstestapp.common.model.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.alex44.digitalnomadstestapp.model.room.NewsArticleRoom
import com.alex44.digitalnomadstestapp.model.room.dao.NewsArticleDao

@Database(entities = [NewsArticleRoom::class], version = 1)
abstract class DatabaseRoom : RoomDatabase() {

    companion object {
        private val DATABASE_NAME = "RoomDatabase.db"

        @Volatile
        private var instance: DatabaseRoom? = null

        @Synchronized
        fun getInstance(): DatabaseRoom {
            if (instance == null) {
                throw RuntimeException("Database have not been created")
            }
            return instance as DatabaseRoom
        }

        fun create(context: Context) {

            //        final Migration migration1_2 = new Migration(1, 2) {
            //            @Override
            //            public void migrate(@NonNull SupportSQLiteDatabase database) {
            //                Timber.d("Migration from 1 to 2");
            //                database.execSQL("");
            //            }
            //        };

            if (instance == null) {
                instance = Room.databaseBuilder(context, DatabaseRoom::class.java, DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    //                    .addMigrations(migration1_2)
                    .build()
            }
        }
    }

    abstract fun getNewsArticleDao() : NewsArticleDao

}