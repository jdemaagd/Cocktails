package com.kryptopass.cocktails.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.intPreferencesKey
import com.kryptopass.cocktails.game.model.RequestState
import com.kryptopass.cocktails.network.Cocktail
import com.kryptopass.cocktails.network.CocktailsApi
import com.skydoves.sandwich.onError
import com.skydoves.sandwich.onFailure
import com.skydoves.sandwich.onSuccess
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import java.io.IOException

class CocktailsRepositoryImpl(
    private val api: CocktailsApi,
    private val preferencesDataStore: DataStore<Preferences>
) : CocktailsRepository {

    override suspend fun getAlcoholic(): RequestState<List<Cocktail>> {
        val cocktailsResponse = api.getAlcoholic()
        var requestState: RequestState<List<Cocktail>> = RequestState.Loading()

        cocktailsResponse.onSuccess {
            requestState = RequestState.Success(data.drinks ?: arrayListOf())
        }
        cocktailsResponse.onError {
            requestState = RequestState.Error()
        }
        cocktailsResponse.onFailure {
            requestState = RequestState.Error()
        }
        return requestState
    }

    override suspend fun saveHighScore(score: Int) {
        Result.runCatching {
            preferencesDataStore.edit { preferences ->
                preferences[HIGH_SCORE_KEY] = score
            }
        }
    }

    override suspend fun getHighScore(): Result<Int> {
        return Result.runCatching {
            val flow = preferencesDataStore.data
                .catch { exception ->
                    /*
                     * dataStore.data throws an IOException when an error
                     * is encountered when reading data
                     */
                    if (exception is IOException) {
                        emit(emptyPreferences())
                    } else {
                        throw exception
                    }
                }
                .map { preferences ->
                    preferences[HIGH_SCORE_KEY]
                }
            flow.firstOrNull() ?: 0
        }
    }

    private companion object {
        private val HIGH_SCORE_KEY = intPreferencesKey("HIGH_SCORE_KEY")
    }
}
