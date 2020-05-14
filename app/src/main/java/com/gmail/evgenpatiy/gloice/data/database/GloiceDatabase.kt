package com.gmail.evgenpatiy.gloice.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.gmail.evgenpatiy.gloice.data.dao.GenreDao
import com.gmail.evgenpatiy.gloice.data.dao.MovieDao
import com.gmail.evgenpatiy.gloice.data.entities.genre.Genre
import com.gmail.evgenpatiy.gloice.data.entities.movie.Movie

@Database(entities = [Movie::class, Genre::class], exportSchema = false, version = 3)
@TypeConverters(GloiceConverter::class)
abstract class GloiceDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao
    abstract fun genreDao(): GenreDao
}