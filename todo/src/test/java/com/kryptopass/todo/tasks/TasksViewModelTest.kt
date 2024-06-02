package com.kryptopass.todo.tasks

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.kryptopass.todo.CoroutineDispatcherRule
import com.kryptopass.todo.Event
import com.kryptopass.todo.R
import com.kryptopass.todo.data.Task
import com.kryptopass.todo.data.source.FakeTestRepository
import com.kryptopass.todo.getOrAwaitValue
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.not
import org.hamcrest.Matchers.nullValue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Unit tests for the implementation of [TasksViewModel]
 */
class TasksViewModelTest {

    // Subject under test
    private lateinit var tasksViewModel: TasksViewModel

    // Use a fake repository to be injected into the viewmodel
    private lateinit var tasksRepository: FakeTestRepository

    // Set the main coroutines dispatcher for unit testing
    @get:Rule
    var mainDispatcherRule = CoroutineDispatcherRule()

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setupViewModel() {
        // We initialise the tasks to 3, with one active and two completed
        tasksRepository = FakeTestRepository()
        val task1 = Task("Title1", "Description1")
        val task2 = Task("Title2", "Description2", true)
        val task3 = Task("Title3", "Description3", true)
        tasksRepository.addTasks(task1, task2, task3)

        tasksViewModel = TasksViewModel(tasksRepository)
    }

    @Test
    fun `Given FAB is clicked when addNewTask is called then AddEditTaskFragment screen opens`() {
        // Act
        tasksViewModel.addNewTask()
        val value = tasksViewModel.newTaskEvent.getOrAwaitValue()

        // Assert
        assertThat(value.getContentIfNotHandled(), not(nullValue()))
    }

    @Test
    fun `Given filter type is all tasks when setFiltering is called then the Add task button is visible`() {
        // Act
        tasksViewModel.setFiltering(TasksFilterType.ALL_TASKS)

        // Act
        assertThat(tasksViewModel.tasksAddViewVisible.getOrAwaitValue(), `is`(true))
    }

    // completeTask_dataAndSnackbarUpdated
    @Test
    fun `Given task is completed then snackbar shows completion message`() {
        // Arrange
        val task = Task("Title", "Description")
        tasksRepository.addTasks(task)

        // Act
        tasksViewModel.completeTask(task, true)

        // Assert
        assertThat(tasksRepository.tasksServiceData[task.id]?.isCompleted, `is`(true))
        val snackbarText: Event<Int> = tasksViewModel.snackbarText.getOrAwaitValue()
        assertThat(snackbarText.getContentIfNotHandled(), `is`(R.string.task_marked_complete))
    }

    // activateTask_dataAndSnackbarUpdated
    @Test
    fun `Given y`() {
        // Arrange
        val task = Task("Title", "Description", true)
        tasksRepository.addTasks(task)

        // Act
        tasksViewModel.completeTask(task, false)

        // Assert
        assertThat(tasksRepository.tasksServiceData[task.id]?.isActive, `is`(true))
        val snackbarText: Event<Int> = tasksViewModel.snackbarText.getOrAwaitValue()
        assertThat(snackbarText.getContentIfNotHandled(), `is`(R.string.task_marked_active))
    }
}
