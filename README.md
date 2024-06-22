# Testing in Android

## Do NOT Test Components

- It has dependencies on the Android Subsystem
- It has dependencies on an external component with a complex interface
- Logic in component cannot be moved out into another object that does not have dependencies on
  Android components

## Examples

- MainActivity: an activity is tied to app lifecycle and Android subsystem
- CocktailsApplication.kt: an app class has many dependencies on Android subsystem

## Tasks

- `todo:dataBindingMergeDependencyArtifactsDebug`

## Looper Error

- Caused by: java.lang.RuntimeException:
- Method getMainLooper in android.os.Looper not mocked.
- See https://developer.android.com/r/studio-ui/build/not-mocked for details.
- typically occurs when you try to execute code that relies on Android framework
    - components like Looper

## Resources

- [Testing Docs](https://developer.android.com/develop/ui/compose/testing#matchers)
- [Testing cheatsheet](https://developer.android.com/develop/ui/compose/testing/testing-cheatsheet)
- [Testing Basics Codelab](https://developer.android.com/codelabs/advanced-android-kotlin-training-testing-basics/#0)
- [Test Doubles & DI Codelab](https://developer.android.com/codelabs/advanced-android-kotlin-training-testing-test-doubles#0)
- [Survey of Testing Topics](https://developer.android.com/codelabs/advanced-android-kotlin-training-testing-survey#0)
- [Compose Sample](https://github.com/android/compose-samples)
- [Compose Pathway](https://developer.android.com/courses/jetpack-compose/course)
