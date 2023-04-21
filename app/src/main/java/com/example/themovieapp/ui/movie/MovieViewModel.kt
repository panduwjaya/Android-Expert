package com.example.themovieapp.ui.movie

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.themovieapp.data.repository.MovieRepository
import dagger.assisted.Assisted
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(private val repository: MovieRepository,
@Assisted state: SavedStateHandle): ViewModel() {

    companion object{
        private const val CURRENT_QUERY = "current_query"
        private const val EMPTY_QUERY = ""
    }

    private val currentQuery = state.getLiveData(CURRENT_QUERY, EMPTY_QUERY)
    val movies = currentQuery.switchMap {query ->
        if (!query.isEmpty()){
            repository.getSearchMovie(query)
        } else {
            repository.getNowPlayingMovie().cachedIn(viewModelScope)
        }
    }

    fun searchMovie(query: String){
        currentQuery.value = query
    }
}