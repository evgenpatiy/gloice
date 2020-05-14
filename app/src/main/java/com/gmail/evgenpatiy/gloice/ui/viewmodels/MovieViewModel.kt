package com.gmail.evgenpatiy.gloice.ui.viewmodels

import android.view.View
import androidx.lifecycle.*
import androidx.paging.PagedList
import com.gmail.evgenpatiy.gloice.data.entities.movie.Movie
import com.gmail.evgenpatiy.gloice.preferences.GloicePreferences
import com.gmail.evgenpatiy.gloice.repository.GloiceRepository
import org.threeten.bp.LocalDate
import javax.inject.Inject

class MovieViewModel @Inject constructor(
    private val repository: GloiceRepository,
    private val preferences: GloicePreferences
) : ViewModel() {

    fun isNetworkConnected(): LiveData<Boolean> = repository.getIsNetworkConnected()

    private val moviesListIsEmpty: LiveData<Boolean>? by lazy {
        moviesListLiveData?.let { rides ->
            Transformations.map(rides) {
                it.count() == 0
            }
        }
    }

    fun isMoviesListEmpty() = moviesListIsEmpty

    private val moviesListLiveData: LiveData<PagedList<Movie>>? by lazy {
        repository.getMostPopularMovies()
    }

    fun getMoviesList() = moviesListLiveData

    private val movieIdLiveData: MutableLiveData<Int>? = MutableLiveData()
    fun setAndSaveMovieId(id: Int) {
        movieIdLiveData?.apply { value = id }
        preferences.saveLastRequestId(id)
    }

    private val movieLiveData: LiveData<Movie>? by lazy {
        movieIdLiveData?.let { Transformations.switchMap(it) { id -> repository.getMovieById(id) } }
    }

    private val movieTitleLiveData: LiveData<String>? by lazy {
        movieLiveData?.let { movie -> Transformations.map(movie) { it.movieTitle } }
    }

    fun getMovieTitle() = movieTitleLiveData

    private val moviePopularityLiveData: LiveData<Double>? by lazy {
        movieLiveData?.let { movie -> Transformations.map(movie) { it.moviePopularity } }
    }

    fun getMoviePopularity() = moviePopularityLiveData

    private val movieVoteAverageLiveData: LiveData<Double>? by lazy {
        movieLiveData?.let { movie -> Transformations.map(movie) { it.movieVoteAverage } }
    }

    fun getMovieVoteAverage() = movieVoteAverageLiveData

    private val movieVoteCountLiveData: LiveData<Int>? by lazy {
        movieLiveData?.let { movie -> Transformations.map(movie) { it.movieVoteCount } }
    }

    fun getMovieVoteCount() = movieVoteCountLiveData

    private val moviePosterPathLiveData: LiveData<String>? by lazy {
        movieLiveData?.let { movie -> Transformations.map(movie) { it.moviePosterPath } }
    }

    fun getMoviePosterPath() = moviePosterPathLiveData

    private val movieAdultMarkerLiveData: LiveData<Boolean>? by lazy {
        movieLiveData?.let { movie -> Transformations.map(movie) { it.isMovieForAdults } }
    }

    fun getMovieAdultMarker() = movieAdultMarkerLiveData

    private val movieOverviewLiveData: LiveData<String>? by lazy {
        movieLiveData?.let { movie -> Transformations.map(movie) { it.movieOverview } }
    }

    fun getMovieOverview() = movieOverviewLiveData

    private val movieReleaseDateLiveData: LiveData<LocalDate>? by lazy {
        movieLiveData?.let { movie -> Transformations.map(movie) { it.movieReleseDate } }
    }

    fun getMovieReleaseDate() = movieReleaseDateLiveData

    private val movieOriginalLanguageLiveData: LiveData<String>? by lazy {
        movieLiveData?.let { movie -> Transformations.map(movie) { it.movieOriginalLanguage } }
    }

    fun getMovieOriginalLanguage() = movieOriginalLanguageLiveData

    private val movieGenresNamesLiveData by lazy {
        movieLiveData?.let {
            Transformations.map(it) { movie ->
                movie.movieGenreIds
            }.switchMap { idsList ->
                repository.getGenreNamesByIds(idsList)
            }
        }
    }

    fun getMovieGenresNames() = movieGenresNamesLiveData

    var navigateOnFailureClick: ((view: View, movieId: Int?) -> Unit)? = null

    fun onNetworkFailureClick(view: View) {
        val movieId = preferences.getLastRequestId()
        movieId?.let { navigateOnFailureClick?.invoke(view, it) }
    }
}
