# Codelab

## Testing Basics

- Instrumented Test
    - needs to run on an emulated or real device
    - almost always use the Android OS or Android framework
- Hamcrest/Truth Assertion libraries
    - assertions read more like human sentences
    - `is` a reserved keyword in kotlin, so to differentiate this as Hamcrest `is`, use back ticks
- APK size configuration
    - implementation: dependency is available in all source sets, including the test source sets
    - testImplementation: dependency is only available in the test source set
    - androidTestImplementation: dependency is only available in the androidTest source set
- Strategies
    - Given, When, Then
    - AAA (Arrange, Act, Assert)
    - Naming: test names are meant to be descriptive
    - TDD: Test Driven Development
- ViewModel Tests
    - application context: without full application activities/fragments, etc.
        - AndroidX Test: application context via `ApplicationProvider.getApplicationContext`
        - Robolectric: simulated Android environment that AndroidX Test uses for local tests
        - AndroidJUnit4: test runner allows for AndroidX Test to run your test differently depending
          on whether they are instrumented or local tests
- LiveData
    - use `InstantTaskExecutorRule` to run LiveData synchronously
    - ensure LiveData observation -> `observeForever` to observe LiveData without a lifecycle
    - causes some code in `InstantTaskExecutorRule` class to be run before and after tests
    - runs all Architecture Components-related background jobs in the same thread so that the test
      results happen synchronously, and in a repeatable order
    - need active observers on LiveData to (without lifecycle owner)
        - trigger any `onChanged` events
        - trigger any `Transformations`

## Dependency Injection and Test Doubles

- Testing Strategy
    - Scope: how much code does test touch?
    - Speed: how fast does the test run?
    - Fidelity: how real-world is it? test that actually makes a network request has more fidelity
- Test Doubles
    - Fake: a `working` implementation of the class (implemented for testing)
    - Mock: tracks which of its methods were called, passes/fails based on getting called correctly
    - Stub: includes no logic and only returns what you program it to return
    - Dummy: passed around but not used, such as if you just need to provide it as a parameter
    - Spy: keeps tracks of some additional information (i.e. number of times a method is called)
- Service Locator: singleton that provides dependencies for regular and test code
- Espresso: integration testing library
    - interact with views, like clicking buttons, sliding a bar, or scrolling down a screen
    - Assert certain views are on screen or are in a certain state
        - such as containing particular text, or that a checkbox is checked, etc.
    - turn off animations to make tests more reliable
        - WindowAnimationScale, TransitionAnimationScale, AnimatorDurationScale
- Mockito: a testing framework that makes test doubles
    - org.mockito:mockito-core -> the Mockito dependency
    - dexmaker-mockito -> required for Android project, enables Mockito to generate classes
    - androidx.test.espresso:espresso-contrib -> testing code for more advanced views,
        - such as DatePicker and RecyclerView

## Testing Coroutines and Jetpack Integrations

- Making asynchronous coroutines synchronous in testing
    - `runBlockingTest` or `runBlocking`
        - `runBlockingTest` handles running code deterministically
            - providing synchronization mechanism
            - skips `delay`, so your tests run faster
            - adds testing related assertions to the end of the coroutine
            - gives you timing control over the coroutine execution
        - `runBlocking` for test doubles that do not need all features of `runBlockingTest`
    - `TestCoroutineDispatcher` for local tests
    - Pausing coroutine execution to test state of code at an exact place in time
- ViewModels: use Dispatcher.Main (uses Android's Looper.getMainLooper())
    - use `setMain()` to modify `Dispatchers.Main` to use `TestCoroutineDispatcher`
