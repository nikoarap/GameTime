# GameTime - Keep track of your favourite sport events

GameTime is an Android application built in Kotlin and following the MVVM design pattern, that helps you keep track of your favourite sport events in real-time. 

## Features
- **Countdown timer displaying the remaining days, hours, minutes and seconds for a sport event, in real-time**
- **You can mark a sport section as "Favourite" so you can keep track of it in the Favourites tab**
- **You can mark a sport event as "Favourite" so you can keep track of it**
- **Expanding a sport section is remembered, so you can have your favourite sport events at hand**
- **Sport events that have started, display the corresponding indicator**
- **Sport events that have started before yesterday, are disregarded and not synced in the app to ensure a better user experience and context**
- **Connectivity callbacks so the data will automatically start downloading if there is an internet connection available**
- **In case the user is offline or the internet connection is somehow lost, proper feedback is given**

## Libraries / Frameworks / Methodologies used in this project

- **Jetpack Compose**
- **Realm Database Structure**
- **Material / Material 3**
- **Kotlin Coroutines (for fetching data & the countdown timer)**
- **MVVM design pattern**
- **LiveData (for event observation)**
- **StateFlow (for data emission to the view)**
- **RealmLiveData (custom object -- LiveData wrapper for Realm Results to ensure smooth data querying and emission)**
- **OkHttp & Retrofit**
- **Flow Layout(for the custom grid displaying the sport events)**
- **Broadcast Receiver for Connectivity Callbacks (old implementation, but used for Android 5 backwards compatibility)**
- **JUnit & Mockito for testing**
- **Custom theming, typography and styles are used when needed**

