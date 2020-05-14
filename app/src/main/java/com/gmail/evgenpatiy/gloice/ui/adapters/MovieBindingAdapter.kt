package com.gmail.evgenpatiy.gloice.ui.adapters

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.gmail.evgenpatiy.gloice.R
import com.gmail.evgenpatiy.gloice.data.database.GloiceConverter
import com.squareup.picasso.Picasso
import org.threeten.bp.LocalDate
import java.util.*
import java.util.stream.Collectors

@BindingAdapter("app:movie_popularity")
fun bindMoviePopularity(view: TextView, popularity: Double?) {
    popularity?.let {
        view.text = it.toString()
    }
}

@BindingAdapter("app:movie_vote_average")
fun bindMovieVoteAverage(view: TextView, voteAverage: Double?) {
    voteAverage?.let {
        view.text = it.toString()
    }
}

@BindingAdapter("app:movie_vote_count")
fun bindMovieVoteCount(view: TextView, voteCount: Int?) {
    voteCount?.let {
        view.text = it.toString()
    }
}

@BindingAdapter("app:movie_adult_marker")
fun bindMovieAdultMarker(view: TextView, adultMarker: Boolean?) {
    adultMarker?.let { isForAdults ->
        if (isForAdults) {
            view.text = view.resources.getString(R.string.movie_adult_yes)
            view.setTextColor(view.resources.getColor(R.color.colorRed, null))
        } else {
            view.text = view.resources.getString(R.string.movie_adult_no)
            view.setTextColor(view.resources.getColor(R.color.colorGreen, null))
        }
    }
}

@BindingAdapter("app:movie_release_date")
fun bindMovieReleaseDate(view: TextView, releaseDate: LocalDate?) {
    releaseDate?.let {
        view.text = GloiceConverter
            .displayedDateFormatter
            .format(it)
    }
}

@BindingAdapter("app:movie_original_language")
fun bindMovieOriginalLanguage(view: TextView, originalLanguage: String?) {
    originalLanguage?.let {
        val locale = Locale(it)
        view.text = locale.getDisplayLanguage(locale)
    }
}

@BindingAdapter("app:movie_genres")
fun bindMovieGenres(view: TextView, genres: List<String>?) {
    genres?.let {
        view.text = genres
            .stream()
            .collect(Collectors.joining(", "))
    }
}

@BindingAdapter("app:movie_poster")
fun bindMoviePoster(view: ImageView, imagePath: String?) {
    val baseUrl = "https://image.tmdb.org/t/p/"
    val posterWidth = "w154"

    imagePath?.let {
        val posterUrl = baseUrl.plus(posterWidth).plus(it)
        Picasso.get()
            .load(posterUrl)
            .placeholder(R.drawable.ic_movie_placeholder)
            .error(R.drawable.ic_error)
            .into(view)
    }
}
