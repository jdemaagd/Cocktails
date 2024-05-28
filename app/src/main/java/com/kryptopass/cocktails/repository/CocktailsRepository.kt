package com.kryptopass.cocktails.repository

import com.kryptopass.cocktails.game.model.RequestState
import com.kryptopass.cocktails.network.Cocktail

interface CocktailsRepository {
    suspend fun getAlcoholic(): RequestState<List<Cocktail>>
    suspend fun saveHighScore(score: Int)
    suspend fun getHighScore(): Result<Int?>
}
