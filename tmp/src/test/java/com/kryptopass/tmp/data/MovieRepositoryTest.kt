package com.kryptopass.tmp.data

import com.kryptopass.tmp.BuildConfig
import com.kryptopass.tmp.model.MovieResponse
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE)
class MovieRepositoryTest {

    private val api = mockk<MovieApi>()
    private val repository = MovieRepository(api, BuildConfig.TMDB_API_KEY)

    @Test
    fun `getPopularMovies returns data successfully`() = runBlocking {
        val sampleResponse = MovieResponse(emptyList())
        coEvery { api.getPopularMovies(any(), any(), any()) } returns sampleResponse

        val response: MovieResponse = repository.getPopularMovies()
        assertNotNull(response)
        assertTrue(response.results.isEmpty())
    }
}
