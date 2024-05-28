package com.kryptopass.cocktails

import com.kryptopass.cocktails.game.model.Question
import com.kryptopass.cocktails.game.model.QuestionImpl
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class QuestionUnitTests {
    private lateinit var question: Question

    @Before
    fun setup() {
        question = QuestionImpl("CORRECT", "INCORRECT")
    }

    @Test
    fun whenCreatingQuestion_shouldNotHaveAnsweredOption() {
        Assert.assertNull(question.answeredOption)
    }

    @Test
    fun whenAnswering_shouldHaveAnsweredOption() {
        question.answer("INCORRECT")

        Assert.assertEquals("INCORRECT", question.answeredOption)
    }

    @Test
    fun whenAnswering_withCorrectOption_shouldReturnTrue() {
        val result = question.answer("CORRECT")

        Assert.assertTrue(result)
    }

    @Test
    fun whenAnswering_withIncorrectOption_shouldReturnFalse() {
        val result = question.answer("INCORRECT")

        Assert.assertFalse(result)
    }

    @Test(expected = IllegalArgumentException::class)
    fun whenAnswering_withInvalidOption_shouldThrowException() {
        question.answer("INVALID")
    }

    @Test
    fun whenCreatingQuestion_shouldReturnOptionsWithCustomSort() {
        val options = question.getOptions { it.reversed() }

        Assert.assertEquals(listOf("INCORRECT", "CORRECT"), options)
    }
}