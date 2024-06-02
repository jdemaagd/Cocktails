package com.kryptopass.todo.statistics

import com.kryptopass.todo.data.Task
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Test

/**
 * Unit tests for [getActiveAndCompletedStats].
 */
class StatisticsUtilsTest {

    @Test
    fun `Given we filter for non-completed tasks when getActiveAndCompletedStats then percent of completed tasks is 0 and percent of active tests is 100`() {
        // Arrange
        val tasks = listOf(
            Task("title", "desc", isCompleted = false)
        )

        // Act
        val result = getActiveAndCompletedStats(tasks)

        // Assert
        assertThat(result.activeTasksPercent, `is`(100f))
        assertThat(result.completedTasksPercent, `is`(0f))
    }

    @Test
    fun `Given we filter for completed tasks when getActiveAndCompletedStats then percent of completed tasks is 100 and percent of active tests is 0`() {
        // Assert
        val tasks = listOf(
            Task("title", "desc", isCompleted = true)
        )

        // Act
        val result = getActiveAndCompletedStats(tasks)

        // Assert
        assertThat(result.activeTasksPercent, `is`(0f))
        assertThat(result.completedTasksPercent, `is`(100f))
    }

    @Test
    fun `Given there are 3 completed tasks and 2 non-completed tasks when getActiveAndCompletedStats is called then percent completed is 60`() {
        // Arrange
        val tasks = listOf(
            Task("title", "desc", isCompleted = true),
            Task("title", "desc", isCompleted = true),
            Task("title", "desc", isCompleted = true),
            Task("title", "desc", isCompleted = false),
            Task("title", "desc", isCompleted = false)
        )

        // Act
        val result = getActiveAndCompletedStats(tasks)

        // Assert
        assertThat(result.activeTasksPercent, `is`(40f))
        assertThat(result.completedTasksPercent, `is`(60f))
    }

    @Test
    fun `Given tasks are null when getActiveAndCompletedStats is called then percent completed is 0`() {
        // Act
        val result = getActiveAndCompletedStats(null)

        // Assert
        assertThat(result.activeTasksPercent, `is`(0f))
        assertThat(result.completedTasksPercent, `is`(0f))
    }

    @Test
    fun `Given there are no tasks when getActiveAndCompletedStats is called then percent completed is 0`() {
        // Act
        val result = getActiveAndCompletedStats(emptyList())

        // Assert
        assertThat(result.activeTasksPercent, `is`(0f))
        assertThat(result.completedTasksPercent, `is`(0f))
    }
}
