package com.kryptopass.movie.ui.screens.list

import com.kryptopass.movie.domain.repository.MovieRepository
import com.kryptopass.movie.model.Movie
import com.kryptopass.movie.model.MovieResponse
import com.kryptopass.movie.ui.screen.list.MovieListViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@ExperimentalCoroutinesApi
@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE)
class MovieListViewModelTest {

    private lateinit var repository: MovieRepository
    private lateinit var viewModel: MovieListViewModel

    @Before
    fun setup() {
        repository = mock()
        viewModel = MovieListViewModel(repository)
    }

    @Test
    fun `fetchMovies updates movies state`() = runTest {
        val sampleMovies = listOf(
            Movie(1, "Sample Movie", "Overview", "/sample.jpg"),
            Movie(2, "Sample Movie 2", "Overview 2", "/sample2.jpg")
        )
        whenever(repository.getPopularMovies()).thenReturn(MovieResponse(sampleMovies))

        viewModel.fetchMovies()

        val movies = viewModel.movies.value
        assertEquals(sampleMovies, movies)
        assertEquals(2, movies.size)
    }
}
