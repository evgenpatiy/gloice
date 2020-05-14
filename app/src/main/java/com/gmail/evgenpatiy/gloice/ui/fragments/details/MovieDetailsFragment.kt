package com.gmail.evgenpatiy.gloice.ui.fragments.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.gmail.evgenpatiy.gloice.databinding.FragmentMovieDetailsBinding
import com.gmail.evgenpatiy.gloice.ui.fragments.AbstractFragment
import com.gmail.evgenpatiy.gloice.ui.viewmodels.MovieViewModel

class MovieDetailsFragment : AbstractFragment() {

    private val viewModel: MovieViewModel? by lazy {
        activity?.let {
            (ViewModelProvider(it, viewModelFactory)[MovieViewModel::class.java]).apply {
                arguments?.let { bundle ->
                    setAndSaveMovieId(MovieDetailsFragmentArgs.fromBundle(bundle).movieId)
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentMovieDetailsBinding.inflate(layoutInflater).apply {
            movieViewModel = viewModel
            lifecycleOwner = viewLifecycleOwner
        }.root
    }
}