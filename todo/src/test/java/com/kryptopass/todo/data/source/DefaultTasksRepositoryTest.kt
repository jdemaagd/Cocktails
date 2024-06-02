package com.kryptopass.todo.data.source

import com.kryptopass.todo.CoroutineDispatcherRule
import com.kryptopass.todo.data.Result.Success
import com.kryptopass.todo.data.Task
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsEqual
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Unit tests for the implementation of the in-memory repository with cache.
 */
@OptIn(ExperimentalCoroutinesApi::class)
class DefaultTasksRepositoryTest {

    private val task1 = Task("Title1", "Description1")
    private val task2 = Task("Title2", "Description2")
    private val task3 = Task("Title3", "Description3")
    private val remoteTasks = listOf(task1, task2).sortedBy { it.id }
    private val localTasks = listOf(task3).sortedBy { it.id }
    private val newTasks = listOf(task3).sortedBy { it.id }

    private lateinit var tasksRemoteDataSource: FakeDataSource
    private lateinit var tasksLocalDataSource: FakeDataSource

    // Class under test
    private lateinit var tasksRepository: DefaultTasksRepository

    // Set the main coroutines dispatcher for unit testing
    @get:Rule
    var coroutineDispatcherRule = CoroutineDispatcherRule()

    @Before
    fun createRepository() {
        tasksRemoteDataSource = FakeDataSource(remoteTasks.toMutableList())
        tasksLocalDataSource = FakeDataSource(localTasks.toMutableList())
        // Get a reference to the class under test
        tasksRepository = DefaultTasksRepository(
            tasksRemoteDataSource, tasksLocalDataSource, Dispatchers.Unconfined
        )
    }

    @Test
    fun `Given we reload from remote data source when getTasks is called then data is from remote data source `() =
        runTest {
            // When tasks are requested from the tasks repository
            val tasks = tasksRepository.getTasks(true) as Success

            // Then tasks are loaded from the remote data source
            assertThat(tasks.data, IsEqual(remoteTasks))
        }
}
