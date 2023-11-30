# GameTime - Keep track of your favourite sport events

GameTime is an Android application that helps you keep track of your favourite sport events in real-time.
App is built in Kotlin, following Clean Architecture Principles and leveraging the Repository & Use Cases patterns along with the MVVM design pattern.
A mock API is used for fetching & displaying virtual sports data.

## Features
- **Countdown timer displaying the remaining days, hours, minutes and seconds for a sport event, in real-time**
- **You can mark a sport section as "Favourite" so you can keep track of it in the Favourites tab**
- **You can mark a sport event as "Favourite" so you can keep track of it**
- **Expanding a sport section is remembered, so you can have your favourite sport events at hand**
- **Sport events that have started, display the corresponding indicator**
- **Sport events that have started before yesterday, are disregarded and not synced in the app to ensure a better user experience and context**
- **Connectivity callbacks so the data will automatically start downloading if there is an internet connection available**
- **In case the user is offline or the internet connection is somehow lost, proper feedback is given**

## What is used in this project:
- **Clean architecture (Repository & Use Cases) with MVVM**
- **Dagger-Hilt Dependency Injection**
- **TDD approach with Unit & UI Test Cases**
- **StateFlow**
- **Jetpack Compose**
- **Coroutines & Compose Effect Handlers**
- **Data Persistence with Room**
- **OkHttp & Retrofit**
- **Material / Material 3**
- **Broadcast Receiver for Connectivity Callbacks**
- **Custom theming, typography and styles are used when needed**

