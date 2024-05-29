package com.kryptopass.cocktails

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.kryptopass.cocktails.game.factory.CocktailsGameFactory
import com.kryptopass.cocktails.game.model.Game
import com.kryptopass.cocktails.game.model.QuestionImpl
import com.kryptopass.cocktails.game.model.RequestState
import com.kryptopass.cocktails.game.model.Score
import com.kryptopass.cocktails.network.Cocktail
import com.kryptopass.cocktails.repository.CocktailsRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestCoroutineScheduler
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class CocktailsViewModelTests {
    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun init_should_CreateAGame_when_FactoryReturnsSuccess() = runTest {
        val game = buildGame()
        val cocktailsViewModel = buildSuccessfulGameViewModel(game, testScheduler)
        cocktailsViewModel.initGame()

        advanceUntilIdle()

        Assert.assertTrue(cocktailsViewModel.game.value is RequestState.Success)
        val successfulRequest = cocktailsViewModel.game.value as RequestState.Success
        Assert.assertEquals(game, successfulRequest.requestObject)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun init_shouldShowFirstQuestion_whenFactoryReturnsSuccess() = runTest {
        val cocktailsViewModel = buildSuccessfulGameViewModel(buildGame(), testScheduler)
        cocktailsViewModel.initGame()
        advanceUntilIdle()

        val question = cocktailsViewModel.question.value

        Assert.assertEquals(questions.first(), question)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun nextQuestion_shouldShowNextQuestion() = runTest {
        val cocktailsViewModel = buildSuccessfulGameViewModel(buildGame(), testScheduler)
        cocktailsViewModel.initGame()

        advanceUntilIdle()
        cocktailsViewModel.nextQuestion()
        advanceUntilIdle()
        val question = cocktailsViewModel.question.value

        Assert.assertEquals(questions.last(), question)
    }

    private val questions = arrayListOf(
        QuestionImpl("Beer", "Wine"),
        QuestionImpl("Martini", "Amarula")
    )

    private fun buildSuccessfulGameViewModel(
        game: Game,
        testScheduler: TestCoroutineScheduler
    ): CocktailsViewModel {
        val cocktailsGameFactorySuccess = buildCocktailsGameFactory(game)
        val standardTestDispatcher = StandardTestDispatcher(testScheduler)

        return CocktailsViewModel(
            fakeRepository,
            cocktailsGameFactorySuccess,
            standardTestDispatcher
        )
    }

    private fun buildGame(): Game {
        return Game(
            questions = questions,
            score = Score(0)
        )
    }

    private fun buildCocktailsGameFactory(game: Game): CocktailsGameFactory {
        return object : CocktailsGameFactory {
            override suspend fun buildGame(): RequestState<Game> {
                return RequestState.Success(
                    game
                )
            }
        }
    }

    private val fakeRepository = object : CocktailsRepository {
        override suspend fun getAlcoholic(): RequestState<List<Cocktail>> {
            TODO("Not yet implemented")
        }

        override suspend fun saveHighScore(score: Int) {
            TODO("Not yet implemented")
        }

        override suspend fun getHighScore(): Result<Int?> {
            TODO("Not yet implemented")
        }
    }
}
