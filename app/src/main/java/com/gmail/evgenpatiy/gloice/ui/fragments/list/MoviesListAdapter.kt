package com.gmail.evgenpatiy.gloice.ui.fragments.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.gmail.evgenpatiy.gloice.R
import com.gmail.evgenpatiy.gloice.data.entities.movie.Movie
import com.gmail.evgenpatiy.gloice.databinding.ViewCardMovieBinding

class MoviesListAdapter(private val onMovieClickListener: OnMovieClickListener) :
    PagedListAdapter<Movie, MovieViewHolder>(movieCallBack) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val movieBinding: ViewCardMovieBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.view_card_movie,
            parent,
            false
        )
        return MovieViewHolder(movieBinding, onMovieClickListener)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie: Movie? = getItem(position)
        holder.bind(movie)
    }

    companion object {
        private val movieCallBack = object :
            DiffUtil.ItemCallback<Movie>() {

            override fun areItemsTheSame(
                oldMovie: Movie,
                newMovie: Movie
            ): Boolean {
                return oldMovie.movieId == newMovie.movieId
            }

            override fun areContentsTheSame(
                oldMovie: Movie,
                newMovie: Movie
            ): Boolean {
                return oldMovie == newMovie
            }
        }
    }
}