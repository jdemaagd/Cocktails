package com.kryptopass.cocktails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kryptopass.cocktails.game.factory.CocktailsGameFactory
import com.kryptopass.cocktails.game.model.Game
import com.kryptopass.cocktails.game.model.Question
import com.kryptopass.cocktails.game.model.RequestState
import com.kryptopass.cocktails.repository.CocktailsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CocktailsViewModel(
    private val repository: CocktailsRepository,
    private val factory: CocktailsGameFactory,
    private val dispatcher: CoroutineDispatcher = IO
) : ViewModel() {

    private val _question = MutableStateFlow<Question?>(null)
    val question: StateFlow<Question?>
        get() = _question

    private val _game = MutableStateFlow<RequestState<Game>>(RequestState.Loading())
    val game: StateFlow<RequestState<Game>>
        get() = _game

    fun initGame() {
        viewModelScope.launch(dispatcher) {
            _game.update {
                factory.buildGame()
            }
            when (game.value) {
                is RequestState.Success -> {
                    _question.update {
                        (game.value as RequestState.Success<Game>).requestObject.nextQuestion()
                    }
                }

                is RequestState.Error -> {

                }

                else -> {

                }
            }
        }
    }

    fun nextQuestion() {
        getGameObject()?.let { rawGame ->
            _question.update {
                rawGame.nextQuestion()
            }
        }
    }

    fun answerQuestion(question: Question, option: String) {
        if (game.value is RequestState.Success) {
            val gameObject = (game.value as RequestState.Success).requestObject
            gameObject.answer(question, option)
            viewModelScope.launch(IO) {
                repository.saveHighScore(gameObject.score.highest)
            }
            nextQuestion()
        }
    }

    private fun getGameObject(): Game? {
        return if (game.value is RequestState.Success) {
            (game.value as RequestState.Success<Game>).requestObject
        } else {
            null
        }
    }
}