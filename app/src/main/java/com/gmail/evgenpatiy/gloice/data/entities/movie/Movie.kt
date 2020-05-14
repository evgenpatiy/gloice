package com.gmail.evgenpatiy.gloice.data.entities.movie

import androidx.room.ColumnInfo
import androidx.room.ColumnInfo.TEXT
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gmail.evgenpatiy.gloice.api.RestApiConstants
import com.gmail.evgenpatiy.gloice.data.database.DatabaseConstants
import com.google.gson.annotations.SerializedName
import org.threeten.bp.LocalDate

@Entity(tableName = DatabaseConstants.moviesTableName)
data class Movie(

    @PrimaryKey()
    @ColumnInfo(name = DatabaseConstants.movieIdColumn)
    @SerializedName(RestApiConstants.movieId)
    val movieId: Int,

    @ColumnInfo(name = DatabaseConstants.movieTitleColumn)
    @SerializedName(RestApiConstants.movieTitle)
    val movieTitle: String,

    @ColumnInfo(name = DatabaseConstants.moviePopularityColumn)
    @SerializedName(RestApiConstants.moviePopularity)
    val moviePopularity: Double,

    @ColumnInfo(name = DatabaseConstants.movieVoteCountColumn)
    @SerializedName(RestApiConstants.movieVoteCount)
    val movieVoteCount: Int,

    @ColumnInfo(name = DatabaseConstants.movieVoteAverageColumn)
    @SerializedName(RestApiConstants.movieVoteAverage)
    val movieVoteAverage: Double,

    @ColumnInfo(name = DatabaseConstants.movieForAdultsColumn)
    @SerializedName(RestApiConstants.movieForAdults)
    val isMovieForAdults: Boolean,

    @ColumnInfo(name = DatabaseConstants.movieOriginalLanguageColumn)
    @SerializedName(RestApiConstants.movieOriginalLanguage)
    val movieOriginalLanguage: String,

    @ColumnInfo(name = DatabaseConstants.moviePosterPathColumn)
    @SerializedName(RestApiConstants.moviePosterPath)
    val moviePosterPath: String,

    @ColumnInfo(name = DatabaseConstants.movieOverviewColumn, typeAffinity = TEXT)
    @SerializedName(RestApiConstants.movieOverview)
    val movieOverview: String,

    @ColumnInfo(name = DatabaseConstants.movieReleseDateColumn)
    @SerializedName(RestApiConstants.movieReleseDate)
    val movieReleseDate: LocalDate,

    @ColumnInfo(name = DatabaseConstants.movieGenreIdsColumn)
    @SerializedName(RestApiConstants.movieGenreIds)
    val movieGenreIds: List<Int>
)