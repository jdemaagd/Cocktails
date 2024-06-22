package com.kryptopass.movie.ui.screen.single

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kryptopass.movie.domain.repository.MovieRepository
import com.kryptopass.movie.model.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val repository: MovieRepository
) : ViewModel() {
    private val _movie = MutableStateFlow<Movie>(Movie(0, "", "", ""))
    val movie: StateFlow<Movie> = _movie

    init {
        fetchMovie(0)
    }

    fun fetchMovie(movieId: Int) {
        viewModelScope.launch {
            try {
                val response = repository.getMovieById(movieId)
                _movie.value = response
            } catch (e: Exception) {
                Log.e("MovieListViewModel", "ERROR: ${e.message}")
            }
        }
    }
}
