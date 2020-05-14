package com.gmail.evgenpatiy.gloice.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.gmail.evgenpatiy.gloice.api.RestApiInterface
import com.gmail.evgenpatiy.gloice.api.RestApiResult
import com.gmail.evgenpatiy.gloice.data.dao.GenreDao
import com.gmail.evgenpatiy.gloice.data.dao.MovieDao
import com.gmail.evgenpatiy.gloice.data.entities.genre.Genre
import com.gmail.evgenpatiy.gloice.data.entities.movie.Movie
import com.gmail.evgenpatiy.gloice.helpers.validator.GloiceValidator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class GloiceRepository @Inject constructor(
    private val movieDao: MovieDao,
    private val genreDao: GenreDao,
    private val api: RestApiInterface,
    private val networkValidator: GloiceValidator
) : BaseRepository {

    private val job = Job()
    private val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main
    private val scope = CoroutineScope(coroutineContext)

    private val fetchedItems = 10
    private var moviesPageToLoad = 0
    private var isGenresRequestRunning = false
    private var isMoviesRequestRunning = false
    private val isNetworkConnected = MutableLiveData<Boolean>(true)

    init {
        receiveGenresList()
        receiveMoviesListPage()
    }

    fun getIsNetworkConnected(): MutableLiveData<Boolean> = isNetworkConnected

    private suspend fun <T : Any> performApiCall(
        call: suspend () -> Response<T>,
        failureMessage: String
    ): T? {

        val result: RestApiResult<T> = getApiResult(call, failureMessage)
        var receivedData: T? = null

        when (result) {
            is RestApiResult.SuccessCall ->
                receivedData = result.data
            is RestApiResult.FailedCall -> {
                Log.d("GLOICE-test API: ", "$failureMessage & Exception - ${result.exception}")
            }
        }
        return receivedData
    }

    private suspend fun <T : Any> getApiResult(
        call: suspend () -> Response<T>,
        failureMessage: String
    ): RestApiResult<T> {
        val apiCallResponse = call.invoke()

        return if (apiCallResponse.isSuccessful) {
            RestApiResult.SuccessCall(apiCallResponse.body()!!)
        } else {
            RestApiResult.FailedCall(IOException("Error getting API call result: $failureMessage"))
        }
    }

    private suspend fun getMovies(page: Int): List<Movie>? {
        val response = performApiCall(
            call = { api.getMostPopularMovies(page).await() },
            failureMessage = "Failure during fetching movies list"
        )
        val moviesList: List<Movie>? = response?.moviesList
        return moviesList
    }

    private suspend fun getGenres(): List<Genre>? {
        val response = performApiCall(
            call = { api.getGenres().await() },
            failureMessage = "Failure during fetching genres list"
        )
        val genresList: List<Genre>? = response?.genresList
        return genresList
    }

    fun receiveMoviesListPage() {
        if (networkValidator.isNetworkConnected()) {
            if (isMoviesRequestRunning) return
            scope.launch {
                isMoviesRequestRunning = true

                // fetch next page
                moviesPageToLoad++
                val popularMovies = getMovies(moviesPageToLoad)
                popularMovies?.let {
                    movieDao.insertList(it)
                }
                isMoviesRequestRunning = false
            }
            isNetworkConnected.value = true
        } else {
            isNetworkConnected.value = false
        }
    }

    private fun receiveGenresList() {
        if (networkValidator.isNetworkConnected()) {
            if (isGenresRequestRunning) return
            scope.launch {
                isGenresRequestRunning = true

                val genres = getGenres()
                genres?.let {
                    genreDao.insertList(it)
                }
                isGenresRequestRunning = false
            }
            isNetworkConnected.value = true
        } else {
            isNetworkConnected.value = false
        }
    }

    private val fetchExecutor: ExecutorService by lazy {
        val threads = Runtime.getRuntime().availableProcessors() + 1
        Executors.newFixedThreadPool(threads)
    }

    private val listConfig: PagedList.Config =
        PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(fetchedItems)
            .build()

    private val boundaryCallback: PagedList.BoundaryCallback<Movie> =
        object : PagedList.BoundaryCallback<Movie>() {
            override fun onItemAtEndLoaded(itemAtEnd: Movie) {
                super.onItemAtEndLoaded(itemAtEnd)
                receiveMoviesListPage()
            }
        }

    override fun getMostPopularMovies(): LiveData<PagedList<Movie>> {
        val moviesDataSource = movieDao.getMostPopularMovies()
        return LivePagedListBuilder(moviesDataSource, listConfig)
            .setBoundaryCallback(boundaryCallback)
            .setFetchExecutor(fetchExecutor)
            .build()
    }

    override fun getMovieById(movieId: Int): LiveData<Movie> = movieDao.getMovieById(movieId)

    fun getGenreNamesByIds(genreIds: List<Int>): LiveData<List<String>> =
        genreDao.getGenreNamesByIds(genreIds)
}