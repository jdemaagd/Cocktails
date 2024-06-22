package com.kryptopass.movie.data

import com.kryptopass.movie.BuildConfig
import com.kryptopass.movie.model.Movie
import com.kryptopass.movie.model.MovieResponse
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test

class MovieRepositoryImplTest {

    private val api = mockk<MovieApi>()
    private val repo = MovieRepositoryImpl(api, BuildConfig.TMDB_API_KEY)

    @Test
    fun `getPopularMovies success`() = runBlocking {
        val emptyListOfMovies = MovieResponse(emptyList())
        coEvery { api.getPopularMovies(any(), any(), any()) } returns emptyListOfMovies

        val response: MovieResponse = repo.getPopularMovies()

        assertNotNull(response)
        assertTrue(response.results.isEmpty())
    }

    @Test
    fun `getPopularMovies returns movie list`() = runBlocking {
        val movies = MovieResponse(
            listOf(
                Movie(
                    id = 1,
                    title = "Movie 1",
                    overview = "Overview 1",
                    posterPath = "Poster 1"
                ),
                Movie(
                    id = 2,
                    title = "Movie 2",
                    overview = "Overview 2",
                    posterPath = "Poster 2"
                )
            )
        )
        coEvery { api.getPopularMovies(any(), any(), any()) } returns movies

        val response: MovieResponse = repo.getPopularMovies()

        assertNotNull(response)
        assertTrue(response.results.count() == 2)
        assertTrue(response.results[0].title == "Movie 1")
        assertTrue(response.results[1].posterPath == "Poster 2")
    }

    @Test
    fun `getMovieById returns one movie`() = runBlocking {
        val movies = MovieResponse(
            listOf(
                Movie(
                    id = 1,
                    title = "Movie 1",
                    overview = "Overview 1",
                    posterPath = "Poster 1"
                ),
                Movie(
                    id = 2,
                    title = "Movie 2",
                    overview = "Overview 2",
                    posterPath = "Poster 2"
                )
            )
        )

        coEvery { api.getMovieById(2) } returns movies.results[1]

        val response: Movie = repo.getMovieById(2)

        assertNotNull(response)
        assertTrue(response.id == 2)
        assertTrue(response.title == "Movie 2")
        assertTrue(response.posterPath == "Poster 2")
        assertTrue(response.overview == "Overview 2")
    }
}
