package com.example.themovieapp.ui.movie

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.themovieapp.R
import com.example.themovieapp.databinding.FragmentMovieBinding
import com.example.themovieapp.ui.adapter.MovieAdapter
import com.example.themovieapp.ui.adapter.MovieLoadStateAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieFragment:Fragment(R.layout.fragment_movie) {
    private val viewModel: MovieViewModel by viewModels<MovieViewModel>()
    private var _binding: FragmentMovieBinding? = null
    private val binding: FragmentMovieBinding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentMovieBinding.bind(view)

        // adapter
        val adapter = MovieAdapter()
        binding.apply {
            rvMovie.setHasFixedSize(true)
            rvMovie.adapter = adapter.withLoadStateHeaderAndFooter(
                header = MovieLoadStateAdapter {adapter.retry()},
                footer = MovieLoadStateAdapter {adapter.retry()}
            )
        }

        viewModel.movies.observe(viewLifecycleOwner){
            adapter.submitData(viewLifecycleOwner.lifecycle,it)
        }
    }
}