package com.kryptopass.cocktails

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.kryptopass.cocktails.game.factory.CocktailsGameFactory
import com.kryptopass.cocktails.game.factory.CocktailsGameFactoryImpl
import com.kryptopass.cocktails.network.CocktailsApi
import com.kryptopass.cocktails.repository.CocktailsRepository
import com.kryptopass.cocktails.repository.CocktailsRepositoryImpl

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
    name = "cocktails"
)

class CocktailsApplication : Application() {
    val repository: CocktailsRepository by lazy {
        CocktailsRepositoryImpl(
            CocktailsApi.create(),
            applicationContext.dataStore
        )
    }

    val gameFactory: CocktailsGameFactory by lazy {
        CocktailsGameFactoryImpl(repository)
    }
}