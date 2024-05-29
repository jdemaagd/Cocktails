# Testing in Android

## Do NOT Test Components

- It has dependencies on the Android Subsystem
- It has dependencies on an external component with a complex interface
- Logic in component cannot be moved out into another object that does not have dependencies on
  Android components

## Examples

- MainActivity: an activity is tied to app lifecycle and Android subsystem
- CocktailsApplication.kt: an app class has many dependencies on Android subsystem
