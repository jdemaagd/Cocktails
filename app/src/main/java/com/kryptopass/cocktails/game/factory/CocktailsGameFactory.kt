package com.kryptopass.cocktails.game.factory

import com.kryptopass.cocktails.game.model.Game
import com.kryptopass.cocktails.game.model.RequestState

interface CocktailsGameFactory {
    suspend fun buildGame(): RequestState<Game>
}