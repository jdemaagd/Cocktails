package com.kryptopass.cocktails.game.factory

import com.kryptopass.cocktails.game.model.Game
import com.kryptopass.cocktails.game.model.QuestionImpl
import com.kryptopass.cocktails.game.model.RequestState
import com.kryptopass.cocktails.game.model.Score
import com.kryptopass.cocktails.network.Cocktail
import com.kryptopass.cocktails.repository.CocktailsRepository

class CocktailsGameFactoryImpl(
    private val repository: CocktailsRepository
) : CocktailsGameFactory {

    override suspend fun buildGame(): RequestState<Game> {
        val highScore = repository.getHighScore().getOrDefault(0)

        val drinkRequestState = repository.getAlcoholic(
        )
        when (drinkRequestState) {
            is RequestState.Success -> {
                return RequestState.Success(
                    Game(
                        questions = buildQuestions(drinkRequestState.requestObject),
                        score = Score(highScore ?: 0)
                    )
                )
            }

            is RequestState.Error -> {
                return RequestState.Error()
            }

            else -> {
                return RequestState.Loading()
            }
        }
    }

    private fun buildQuestions(cocktailList: List<Cocktail>) = cocktailList.map { cocktail ->
        val otherCocktail = cocktailList.shuffled().first { it != cocktail }
        QuestionImpl(cocktail.strDrink, otherCocktail.strDrink, cocktail.strDrinkThumb)
    }
}