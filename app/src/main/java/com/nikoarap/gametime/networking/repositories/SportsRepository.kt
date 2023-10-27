package com.nikoarap.gametime.networking.repositories

import android.util.Log
import com.nikoarap.gametime.networking.apiServices.RetrofitClient
import com.nikoarap.gametime.networking.transforming.DTOs.SportModelDTO
import com.nikoarap.gametime.networking.transforming.transformers.SportModelTransformer
import com.nikoarap.gametime.storage.SportsStorage
import com.nikoarap.gametime.utils.Constants.Companion.VALUE_ZERO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.HttpException
import retrofit2.Response

class SportsRepository {

    private val sportModelTransformer = SportModelTransformer()
    private val CLASS_TAG: String = SportsRepository::class.java.simpleName

    suspend fun getIfSuccessful() {
        val response = getSportsData()
        if (response.isSuccessful) {
            response.body()?.let { persistData(it) }
        } else {
            onFetchError(response.errorBody())
        }
    }

    private suspend fun getSportsData(): Response<List<SportModelDTO>> {
        return withContext(Dispatchers.IO) {
            try {
                val apiService = RetrofitClient.retrofitInstance
                apiService.getSportModels()
            } catch (e: Exception) {
                val errorCode = if (e is HttpException) e.code() else VALUE_ZERO
                Log.e(CLASS_TAG, "getSportsData failed with error code: $errorCode and message: ${e.message}")
                val throwableMessage: String = e.cause?.message.toString()
                Response.error(errorCode, throwableMessage.toResponseBody(null))
            }
        }
    }

    private fun persistData(sportModelDTOList: List<SportModelDTO>) {
        for (sportModelDTO in sportModelDTOList) {
            SportsStorage.insert(sportModelTransformer.fromDTO(sportModelDTO))
        }
    }

    private fun onFetchError(throwable: ResponseBody?) {
     //todo handle the error by sending an event to the main activity

    }


}