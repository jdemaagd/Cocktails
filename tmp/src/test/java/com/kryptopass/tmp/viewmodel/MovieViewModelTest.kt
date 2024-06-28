package com.kryptopass.tmp.viewmodel

import com.kryptopass.tmp.data.MovieRepository
import com.kryptopass.tmp.model.Movie
import com.kryptopass.tmp.model.MovieResponse
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.*
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE)
class MovieViewModelTest {

    private lateinit var repository: MovieRepository
    private lateinit var viewModel: MovieViewModel

    @Before
    fun setup() {
        repository = mock()
        viewModel = MovieViewModel(repository)
    }

    @Test
    fun `fetchMovies updates movies state`() = runTest {
        val sampleMovies = listOf(Movie(1, "Sample Movie", "Overview", "/sample.jpg"))
        whenever(repository.getPopularMovies()).thenReturn(MovieResponse(sampleMovies))

        viewModel.fetchMovies()

        val movies = viewModel.movies.value
        assertEquals(sampleMovies, movies)
    }
}
