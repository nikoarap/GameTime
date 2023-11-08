buildscript {
    dependencies {
        classpath("io.realm:realm-gradle-plugin:10.15.1")
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.48.1")
    }
}

plugins {
    id("com.android.application") version "8.1.2" apply false
    id("org.jetbrains.kotlin.android") version "1.8.10" apply false
    id("io.realm.kotlin") version "1.4.0" apply false
    kotlin("kapt") version "1.8.10"
}