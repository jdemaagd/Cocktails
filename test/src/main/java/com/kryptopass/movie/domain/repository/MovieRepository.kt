package com.kryptopass.movie.domain.repository

import com.kryptopass.movie.model.Movie
import com.kryptopass.movie.model.MovieResponse

interface MovieRepository {
    suspend fun getPopularMovies(): MovieResponse
    suspend fun getMovieById(id: Int): Movie
}
