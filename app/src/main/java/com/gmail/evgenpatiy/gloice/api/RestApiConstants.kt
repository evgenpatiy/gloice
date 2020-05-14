package com.gmail.evgenpatiy.gloice.api

class RestApiConstants {

    companion object {

        //region movies
        const val moviesListPage = "page"
        const val moviesListTotalResults = "total_results"
        const val moviesListTotalPages = "total_pages"
        const val moviesList = "results"

        const val movieId = "id"
        const val movieTitle = "title"
        const val moviePopularity = "popularity"
        const val movieVoteCount = "vote_count"
        const val movieVoteAverage = "vote_average"
        const val movieForAdults = "adult"
        const val movieOriginalLanguage = "original_language"
        const val moviePosterPath = "poster_path"
        const val movieOverview = "overview"
        const val movieReleseDate = "release_date"
        const val movieGenreIds = "genre_ids"
        //endregion

        // region genres
        const val genres = "genres"
        const val genreId = "id"
        const val genreName = "name"
        //endregion
    }
}