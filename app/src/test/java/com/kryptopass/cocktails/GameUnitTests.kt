package com.kryptopass.cocktails

import com.kryptopass.cocktails.game.model.Game
import com.kryptopass.cocktails.game.model.Question
import com.kryptopass.cocktails.game.model.QuestionImpl
import com.kryptopass.cocktails.game.model.Score
import org.junit.Assert
import org.junit.Test

class GameUnitTests {
    @Test
    fun whenGettingNextQuestion_shouldReturnIt() {
        val question1 = QuestionImpl("CORRECT", "INCORRECT")
        val questions = listOf(question1)
        val game = Game(questions)

        val nextQuestion = game.nextQuestion()

        Assert.assertSame(question1, nextQuestion)
    }

    @Test
    fun whenGettingNextQuestion_withoutMoreQuestions_shouldReturnNull() {
        val question1 = QuestionImpl("CORRECT", "INCORRECT")
        val questions = listOf(question1)
        val game = Game(questions)

        game.nextQuestion()
        val nextQuestion = game.nextQuestion()

        Assert.assertNull(nextQuestion)
    }

    @Test
    fun whenAnsweringCorrectly_shouldIncrementCurrentScore() {
        val question = createQuestion(true)
        val score = Score()
        val game = Game(listOf(question), score)

        game.answer(question, "OPTION")

        Assert.assertEquals(score.current, 1)
    }

    @Test
    fun whenAnsweringIncorrectly_shouldNotIncrementCurrentScore() {
        val question = createQuestion(false)
        val score = Score()
        val game = Game(listOf(question), score)

        game.answer(question, "OPTION")

        Assert.assertEquals(score.current, 0)
    }

    private fun createQuestion(answerReturn: Boolean): Question {
        return object : Question("", "") {
            override fun answer(option: String): Boolean {
                return answerReturn
            }
        }
    }
}