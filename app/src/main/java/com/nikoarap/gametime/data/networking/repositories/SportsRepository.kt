package com.nikoarap.gametime.data.networking.repositories

import android.util.Log
import com.nikoarap.gametime.data.networking.apiServices.RetrofitClient
import com.nikoarap.gametime.data.networking.dto.SportModelDto
import com.nikoarap.gametime.data.storage.DataStorage
import com.nikoarap.gametime.utils.Constants.BAD_REQUEST
import com.nikoarap.gametime.utils.ListUtils
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.HttpException
import retrofit2.Response

/**
 * Repository class responsible for fetching sports data and storing it locally.
 */
class SportsRepository {

    private val CLASS_TAG: String = SportsRepository::class.java.simpleName

//    /**
//     * Fetch sports data from the API and persist it locally.
//     */
//    suspend fun fetchData() {
//        val response = getSportsData()
//        if (response.isSuccessful) {
//            response.body()?.let { persistData(it) }
//        } else {
//            onFetchError(response.errorBody())
//        }
//    }

//    /**
//     * Retrieve sports data from the API.
//     *
//     * @return A Response containing SportModelListDTO.
//     */
//    private suspend fun getSportsData(): List<SportModelDto> {
//        return try {
//            val apiService = RetrofitClient.retrofitInstance
//            apiService.getSportModels()
//        } catch (e: Exception) {
//            val errorCode = if (e is HttpException) e.code() else BAD_REQUEST
//            Log.e(CLASS_TAG, "getSportsData failed with exception: " + e.cause)
//            Log.e(CLASS_TAG, "getSportsData failed with error code: $errorCode and message: ${e.message}")
//            val throwableMessage: String = e.cause?.message.toString()
//            Response.error(errorCode, throwableMessage.toResponseBody(null))
//        }
//    }

//    /**
//     * Persist sports data locally after merging.
//     *
//     * @param sportModelDTOList The list of SportModelDTO objects.
//     */
//    private fun persistData(sportModelDTOList: List<SportModelDto>) {
//        val mergedDTOList = ListUtils.mergeSportModels(sportModelDTOList)
//        for (sportModelDTO in mergedDTOList) {
//            DataStorage.insert(sportModelDTO)
//        }
//    }

    /**
     * Handle errors during data fetch.
     *
     * @param throwable The error response as ResponseBody.
     */
    private fun onFetchError(throwable: ResponseBody?) {
     //todo handle the error by sending an event to the main activity
    }
}