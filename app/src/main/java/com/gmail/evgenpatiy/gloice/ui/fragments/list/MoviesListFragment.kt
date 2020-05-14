package com.gmail.evgenpatiy.gloice.ui.fragments.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.gmail.evgenpatiy.gloice.databinding.FragmentMovieListBinding
import com.gmail.evgenpatiy.gloice.ui.fragments.AbstractFragment
import com.gmail.evgenpatiy.gloice.ui.viewmodels.MovieViewModel

class MoviesListFragment : AbstractFragment() {

    private val viewModel: MovieViewModel? by lazy {
        activity?.let {
            ViewModelProvider(it, viewModelFactory)[MovieViewModel::class.java].apply {
                navigateOnFailureClick = navigateToLastOpenedMovie
            }
        }
    }

    private val onMovieClickListener: OnMovieClickListener = object : OnMovieClickListener {
        override fun onClick(view: View, movieId: Int?) {
            movieId?.let { navigateMovieDetailsById(view, it) }
        }
    }

    private val listAdapter: MoviesListAdapter = MoviesListAdapter(onMovieClickListener)

    private val backPressedCallback: OnBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            activity?.finish()
        }
    }

    private val navigateToLastOpenedMovie: (view: View, lastMovieId: Int?) -> Unit =
        { view, lastMovieId ->
            lastMovieId?.let {
                if (it != 0) {
                    navigateMovieDetailsById(view, it)
                } else {
                    navigateMovieError(view)
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(this, backPressedCallback)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentMovieListBinding.inflate(inflater, container, false)
            .apply {
                movieViewModel = viewModel
                lifecycleOwner = viewLifecycleOwner

                viewModel?.getMoviesList()?.observe(
                    viewLifecycleOwner,
                    Observer { movies ->
                        listAdapter.submitList(movies)
                    })

                moviesRecyclerview.apply {
                    layoutManager = LinearLayoutManager(context)
                    adapter = listAdapter
                    setHasFixedSize(true)
                }
            }.root
    }

    private fun navigateMovieDetailsById(view: View, movieId: Int) {
        Navigation.findNavController(view)
            .navigate(MoviesListFragmentDirections.toMovieDetails(movieId))
    }

    private fun navigateMovieError(view: View) {
        Navigation.findNavController(view)
            .navigate(MoviesListFragmentDirections.toMovieError())
    }
}
