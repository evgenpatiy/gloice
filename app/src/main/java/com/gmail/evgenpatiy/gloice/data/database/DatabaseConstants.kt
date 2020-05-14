package com.gmail.evgenpatiy.gloice.data.database

class DatabaseConstants {

    companion object {
        const val databaseName = "com.gmail.evgenpatiy.gloice_db"
        const val datePattern = "yyyy-MM-dd"
        const val movieDetailsDatePattern = "MMMM dd, yyyy"

        //region movies
        const val moviesTableName = "gloice_movies"
        const val movieIdColumn = "movie_id"
        const val movieTitleColumn = "movie_title"
        const val moviePopularityColumn = "movie_popularity"
        const val movieVoteCountColumn = "movie_vote_count"
        const val movieVoteAverageColumn = "movie_vote_average"
        const val movieForAdultsColumn = "movie_for_adults"
        const val movieOriginalLanguageColumn = "movie_original_language"
        const val moviePosterPathColumn = "movie_poster_path"
        const val movieOverviewColumn = "movie_overview"
        const val movieReleseDateColumn = "movie_release_date"
        const val movieGenreIdsColumn = "movie_genre_ids"
        //endregion

        //region genres
        const val genresTableName = "gloice_genres"
        const val genreIdColumn = "genre_id"
        const val genreNameColumn = "genre_name"
        //endregion
    }
}