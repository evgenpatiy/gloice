package com.gmail.evgenpatiy.gloice.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.gmail.evgenpatiy.gloice.data.entities.movie.Movie

interface BaseRepository {

    fun getMostPopularMovies(): LiveData<PagedList<Movie>>
    fun getMovieById(movieId: Int): LiveData<Movie>
}