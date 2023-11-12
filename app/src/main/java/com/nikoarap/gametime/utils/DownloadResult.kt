package com.nikoarap.gametime.utils

sealed class DownloadResult<T>(val data: T? = null, val errorMessage: String? = null) {
    class Success<T>(data: T?): DownloadResult<T>(data)
    class Error<T>(errorMessage: String, data: T? = null): DownloadResult<T>(data, errorMessage)
    class Loading<T>(data: T? = null): DownloadResult<T>(data)
}