package com.gmail.evgenpatiy.gloice.data.dao

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Query
import com.gmail.evgenpatiy.gloice.data.database.DatabaseConstants
import com.gmail.evgenpatiy.gloice.data.entities.movie.Movie

@Dao
interface MovieDao : AbstractDao<Movie> {

    @Query(
        "SELECT * FROM " + DatabaseConstants.moviesTableName
                + " ORDER BY " + DatabaseConstants.moviePopularityColumn + " DESC"
    )
    fun getMostPopularMovies(): DataSource.Factory<Int, Movie>

    @Query(
        "SELECT * FROM " + DatabaseConstants.moviesTableName
                + " WHERE " + DatabaseConstants.movieIdColumn + " = :movieId"
    )
    fun getMovieById(movieId: Int): LiveData<Movie>
}