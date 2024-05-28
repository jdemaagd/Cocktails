package com.kryptopass.cocktails

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.kryptopass.cocktails.ui.theme.CocktailsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CocktailsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val cocktailsApplication = (application as CocktailsApplication)
                    CocktailsScreen(
                        repository = cocktailsApplication.repository,
                        gameFactory = cocktailsApplication.gameFactory
                    )
                }
            }
        }
    }
}
