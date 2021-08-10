package com.artemissoftware.hermesnews.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.artemissoftware.hermesnews.models.Article
import com.artemissoftware.hermesnews.util.DatabaseConstants.Companion.DATABASE_VERSION

@Database(
    entities = [Article::class],
    version = DATABASE_VERSION,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class NewsDataBase: RoomDatabase() {

    abstract fun articleDao(): ArticleDao

}

