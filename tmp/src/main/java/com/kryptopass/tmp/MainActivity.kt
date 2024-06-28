package com.kryptopass.tmp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import com.kryptopass.tmp.data.MovieApi
import com.kryptopass.tmp.data.MovieRepository
import com.kryptopass.tmp.ui.MovieListScreen
import com.kryptopass.tmp.ui.theme.TestTheme
import com.kryptopass.tmp.viewmodel.MovieViewModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val repository = MovieRepository(Api.movieApi, BuildConfig.TMDB_API_KEY)
            val viewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : androidx.lifecycle.ViewModel> create(modelClass: Class<T>): T {
                    return MovieViewModel(repository) as T
                }
            })[MovieViewModel::class.java]

            TestTheme {
                MovieListScreen(viewModel)
            }
        }
    }
}

object Api {
    private val retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.TMDB_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val movieApi: MovieApi = retrofit.create(MovieApi::class.java)
}
