package com.kryptopass.todo.statistics

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.kryptopass.todo.CoroutineDispatcherRule
import com.kryptopass.todo.data.source.FakeTestRepository
import com.kryptopass.todo.getOrAwaitValue
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Unit tests for the implementation of [StatisticsViewModel]
 */
class StatisticsViewModelTest {

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    // Subject under test
    private lateinit var statisticsViewModel: StatisticsViewModel

    // Use a fake repository to be injected into the viewmodel
    private lateinit var tasksRepository: FakeTestRepository

    // Set the main coroutines dispatcher for unit testing.
    @get:Rule
    var coroutineDispatcherRule = CoroutineDispatcherRule()

    @Before
    fun setupStatisticsViewModel() {
        // We initialise the repository with no tasks
        tasksRepository = FakeTestRepository()

        statisticsViewModel = StatisticsViewModel(tasksRepository)
    }

    @Test
    fun loadStatisticsWhenTasksAreUnavailable_callErrorToDisplay() {
        // Make the repository return errors
        tasksRepository.setReturnError(true)
        statisticsViewModel.refresh()

        // Then an error message is shown
        assertThat(statisticsViewModel.empty.getOrAwaitValue(), `is`(true))
        assertThat(statisticsViewModel.error.getOrAwaitValue(), `is`(true))
    }

//    @Test
//    fun loadTasks_loading() {
//        // Pause dispatcher so we can verify initial values
//        mainDispatcherRule.pauseDispatcher()
//
//        // Load the task in the viewmodel
//        statisticsViewModel.refresh()
//
//        // Then progress indicator is shown
//        assertThat(statisticsViewModel.dataLoading.getOrAwaitValue(), `is`(true))
//
//        // Execute pending coroutines actions
//        mainDispatcherRule.resumeDispatcher()
//
//        // Then progress indicator is hidden
//        assertThat(statisticsViewModel.dataLoading.getOrAwaitValue(), `is`(false))
//    }
}
