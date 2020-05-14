package com.gmail.evgenpatiy.gloice.data.entities.movie

import com.gmail.evgenpatiy.gloice.api.RestApiConstants
import com.google.gson.annotations.SerializedName

data class PopularMoviesList(

    @SerializedName(RestApiConstants.moviesListPage)
    val moviesListPage: Int,

    @SerializedName(RestApiConstants.moviesListTotalResults)
    val moviesListTotalResults: Int,

    @SerializedName(RestApiConstants.moviesListTotalPages)
    val moviesListTotalPages: Int,

    @SerializedName(RestApiConstants.moviesList)
    val moviesList: List<Movie>
)