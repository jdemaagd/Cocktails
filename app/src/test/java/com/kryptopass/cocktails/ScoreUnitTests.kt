package com.kryptopass.cocktails

import com.kryptopass.cocktails.game.model.Score
import org.junit.Assert
import org.junit.Test

class ScoreUnitTests {
    @Test
    fun whenIncrementingScore_shouldIncrementCurrentScore() {
        val score = Score()

        score.increment()

        Assert.assertEquals("Current score should have been 1", 1, score.current)
    }

    @Test
    fun whenIncrementingScore_aboveHighScore_shouldAlsoIncrementHighScore() {
        val score = Score()

        score.increment()

        Assert.assertEquals(1, score.highest)
    }
}