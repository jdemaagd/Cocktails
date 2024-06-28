package com.kryptopass.tmp.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kryptopass.tmp.data.MovieRepository
import com.kryptopass.tmp.model.Movie
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MovieViewModel(
    private val repository: MovieRepository,
    // private val usecaseGetMovieList
) : ViewModel() {
    private val _movies = MutableStateFlow<List<Movie>>(emptyList())
    val movies: StateFlow<List<Movie>> = _movies

    init {
        fetchMovies()
    }

    fun fetchMovies() {
        viewModelScope.launch {
            try {
                val response = repository.getPopularMovies()
                _movies.value = response.results

            } catch (e: Exception) {
                Log.e("MovieViewModel", "ERROR: ${e.message}")
            }
        }
    }

    private fun processSomething() {

    }
}
