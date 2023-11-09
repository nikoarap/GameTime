package com.nikoarap.gametime.data.networking.deserializers

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.nikoarap.gametime.data.networking.dto.EventModelDto
import com.nikoarap.gametime.utils.JsonUtils
import java.lang.reflect.Type

/**
 * A custom JSON deserializer for converting a JSON object to an [EventModelDto] object.
 * This deserializer is used to parse specific fields from a JSON object and construct an [EventModelDto] instance.
 */
class EventModelDeserializer: JsonDeserializer<EventModelDto> {

    private val caughtException = MutableLiveData<Boolean>()

    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): EventModelDto {
        var eventModelDTO = EventModelDto()

        try {
            eventModelDTO = EventModelDto(
                JsonUtils.getAsStringFromJsonObject("i", json?.asJsonObject),
                JsonUtils.getAsStringFromJsonObject("si", json?.asJsonObject),
                JsonUtils.getAsStringFromJsonObject("d", json?.asJsonObject),
                JsonUtils.getAsLongFromJsonObject("tt", json?.asJsonObject)
            )
        } catch (e: Exception) {
            caughtException.postValue(true)
            return eventModelDTO
        }

        return eventModelDTO
    }

    fun getErrorLiveData(): LiveData<Boolean> = caughtException

    fun resetErrorLiveData() {
        caughtException.value = false
    }
}