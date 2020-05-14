package com.gmail.evgenpatiy.gloice.ui.fragments.list

import androidx.recyclerview.widget.RecyclerView
import com.gmail.evgenpatiy.gloice.data.entities.movie.Movie
import com.gmail.evgenpatiy.gloice.databinding.ViewCardMovieBinding

class MovieViewHolder(
    private val movieCardBinding: ViewCardMovieBinding,
    private val onMovieClickListener: OnMovieClickListener
) :
    RecyclerView.ViewHolder(movieCardBinding.root) {

    fun bind(item: Movie?) {
        item?.let {
            movieCardBinding.apply {
                movie = it
                listener = onMovieClickListener
                executePendingBindings()
            }
        }
    }
}