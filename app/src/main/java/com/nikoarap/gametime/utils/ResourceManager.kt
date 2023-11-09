package com.nikoarap.gametime.utils

sealed class ResourceManager<T>(val data: T? = null, val errorMessage: String? = null) {
    class Success<T>(data: T?): ResourceManager<T>(data)
    class Error<T>(errorMessage: String, data: T? = null): ResourceManager<T>(data, errorMessage)
    class Loading<T>(data: T? = null): ResourceManager<T>(data)
}