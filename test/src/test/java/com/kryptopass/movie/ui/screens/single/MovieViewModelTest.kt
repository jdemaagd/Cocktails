package com.kryptopass.movie.ui.screens.single

import com.kryptopass.movie.domain.repository.MovieRepository
import com.kryptopass.movie.model.Movie
import com.kryptopass.movie.ui.screen.single.MovieViewModel
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
        val sampleMovie = Movie(1, "Sample Movie", "Overview", "/sample.jpg")
        whenever(repository.getMovieById(1)).thenReturn(sampleMovie)

        viewModel.fetchMovie(1)

        val movies = viewModel.movie.value
        assertEquals(sampleMovie, movies)
    }
}
