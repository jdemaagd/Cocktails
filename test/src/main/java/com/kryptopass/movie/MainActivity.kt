package com.kryptopass.movie

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kryptopass.movie.ui.screen.list.MovieListScreen
import com.kryptopass.movie.ui.screen.single.MovieScreen
import com.kryptopass.movie.ui.theme.MovieTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieTheme {
                AppNavigation()
            }
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "movies") {
        composable("movies") { MovieListScreen(navController, hiltViewModel()) }
        composable("movies/{id}") { backStackEntry ->
            MovieScreen(
                movieId = backStackEntry.arguments?.getString("id")?.toInt()
                    ?: error("Missing id in arguments"),
                hiltViewModel()
            )
        }
    }
}
