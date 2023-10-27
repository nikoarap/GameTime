buildscript {
    dependencies {
        classpath("io.realm:realm-gradle-plugin:10.11.1")
    }
}

plugins {
    id("com.android.application") version "8.1.2" apply false
    id("org.jetbrains.kotlin.android") version "1.8.10" apply false
    id("io.realm.kotlin") version "1.4.0" apply false
}