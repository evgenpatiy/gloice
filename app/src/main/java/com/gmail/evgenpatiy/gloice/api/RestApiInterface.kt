package com.gmail.evgenpatiy.gloice.api

import com.gmail.evgenpatiy.gloice.data.entities.genre.GenresList
import com.gmail.evgenpatiy.gloice.data.entities.movie.PopularMoviesList
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RestApiInterface {

    @GET("movie/popular")
    fun getMostPopularMovies(@Query("page") page: Int): Deferred<Response<PopularMoviesList>>

    @GET("genre/movie/list")
    fun getGenres(): Deferred<Response<GenresList>>
}