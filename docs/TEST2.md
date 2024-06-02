# Advanced Android in Kotlin Testing

## Given, When, Then

- Given: Setup the objects and the state of the app that you need for your test.
    - For this test, what is "given" is that you have a list of tasks where the task is active.
- When: Do the actual action on the object you are testing.
    - For this test, it means calling `getActiveAndCompletedStats`.
- Then: This is where you actually check what happens when you do the action where you check if the
  test passed or failed.
    - This is usually a number of assert function calls.
    - For this test, it is two asserts that check you have correct active and completed percentages.

## Example `subjectUnderTest_actionOrInput_resultState`

- Subject under test is the method or class that is being tested (`getActiveAndCompletedStats`).
- Next is the action or input (`noCompleted`).
- Finally you have the expected result (`returnsHundredZero`).

## Testing Pyramid

- Strategy
    - Scope: How much of the code does the test touch?
        - Tests can run on a single method, across the entire application, or somewhere in between.
    - Speed: How fast does the test run?
        - Test speeds can vary from milli-seconds to several minutes.
    - Fidelity: How "real-world" is the test?
        - For example, if part of the code you are testing needs to make a network request, does the
          test code actually make this network request, or does it fake the result?
        - If the test actually talks with the network, this means it has higher fidelity.
        - The trade-off is that the test could take longer to run, could result in errors if the
          network is down, or could be costly to use.
- Categories
    - Unit: Highly focused tests that run on a single class, usually a single method in that class.
        - If a unit test fails, you can know exactly where in your code the issue is.
        - They have low fidelity since in the real world, your app involves much more than the
          execution of one method or class.
        - They are fast enough to run every time you change your code.
        - They will most often be locally run tests (in the test source set).
        - Example: Testing single methods in view models and repositories.
    - Integration: Test interaction of several classes to make sure they behave as expected when
      used together.
        - One way to structure integration tests is to have them test a single feature, such as the
          ability to save a task.
        - They test a larger scope of code than unit tests, but are still optimized to run fast,
          versus having full fidelity.
        - They can be run either locally or as instrumentation tests, depending on the situation.
        - Example: Testing all the functionality of a single fragment and view model pair.
    - End-to-End: Test a combination of features working together.
        - They test large portions of the app, simulate real usage closely, and therefore are
          usually slow.
        - They have highest fidelity and tell you that your application actually works as a whole.
        - By and large, these tests will be instrumented tests (in the androidTest source set).
        - Example: Starting up the entire app and testing a few features together.

## Test Doubles

- Fake: A test double that has a "working" implementation of the class, but it is implemented in a
  way that makes it good for tests but unsuitable for production.
- Mock: A test double that tracks which of its methods were called.
    - It then passes or fails a test depending on whether it's methods were called correctly.
- Stub: A test double that includes no logic and only returns what you program it to return.
    - A StubTaskRepository could be programmed to return certain combinations of tasks from getTasks
      for example.
- Dummy: A test double that is passed around but not used, such as if you just need to provide it as
  a parameter.
    - If you had a NoOpTaskRepository, it would just implement the TaskRepository with no code in
      any of the methods.
- Spy: A test double which also keeps tracks of some additional information; for example, if you
  made a SpyTaskRepository, it might keep track of number of times addTask method was called.

## Espresso Testing

- Disable 3 Settings -> Settings -> Developer options
    - Window animation scale
    - Transition animation scale
    - Animator duration scale

## Resources

- [Testing Basics](https://developer.android.com/codelabs/advanced-android-kotlin-training-testing-basics?index=..%2F..index#0)
- [Introduction to Test Doubles and Dependency Injection](https://developer.android.com/codelabs/advanced-android-kotlin-training-testing-test-doubles#0)
- [Testing Coroutines and Jetpack Integrations](https://developer.android.com/codelabs/advanced-android-kotlin-training-testing-survey#0)
- [Architecture Blueprints](https://github.com/android/architecture-samples)
- [Android Kotlin Fundamentals Training Codelabs](https://developer.android.com/courses/android-basics-compose/course)
- [Room with a View Codelab](https://developer.android.com/codelabs/android-room-with-a-view-kotlin#0)
- [Android Sunflower Sample](https://github.com/android/sunflower)
- [Developing Android Apps with Kotlin Udacity Training Course](https://www.udacity.com/course/developing-android-apps-with-kotlin--ud9012)
- [Test from the Command Line](https://developer.android.com/studio/test/command-line)
- [Hamcrest Tutorial](https://hamcrest.org/JavaHamcrest/tutorial)
- [Unit-testing LiveData and other common observability problems](https://medium.com/androiddevelopers/unit-testing-livedata-and-other-common-observability-problems-bb477262eb04)
- [Easy Coroutines in Android: viewModelScope](https://medium.com/androiddevelopers/easy-coroutines-in-android-viewmodelscope-25bffb605471)
- [Testing Coroutines on Android](https://www.youtube.com/watch?v=KMb0Fs8rCRs)
