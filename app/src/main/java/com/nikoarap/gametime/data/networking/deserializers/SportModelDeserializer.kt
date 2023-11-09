package com.nikoarap.gametime.data.networking.deserializers

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.JsonArray
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import com.nikoarap.gametime.data.networking.dto.EventModelDto
import com.nikoarap.gametime.data.networking.dto.SportModelDto
import com.nikoarap.gametime.utils.Constants.Companion.EMPTY_STRING
import com.nikoarap.gametime.utils.JsonUtils
import java.lang.reflect.Type

/**
 * A custom JSON deserializer for converting a JSON object to a [SportModelListDto] object.
 * This deserializer is used to parse and construct a list of [SportModelDto] instances and
 * populate a [SportModelListDto] object with the parsed data.
 */
class SportModelDeserializer: JsonDeserializer<List<SportModelDto>> {

    val sportModelDTOsList: ArrayList<SportModelDto> = arrayListOf()
    private val caughtException = MutableLiveData<Boolean>()

    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): List<SportModelDto> {
        val sportModelListDTO: ArrayList<SportModelDto> = arrayListOf()

        try {
            val jsonArrayObject = json?.asJsonArray

            if (jsonArrayObject != null) {
                for (jsonElement in jsonArrayObject.asJsonArray) {
                    val sportModelDto = SportModelDto()

                    if (jsonElement.asJsonObject.has("i")) {
                        if (jsonElement.asJsonObject.get("i").isJsonPrimitive && jsonElement.asJsonObject.get("i").asString != null) {
                            sportModelDto.id = jsonElement.asJsonObject.get("i")?.asString ?: EMPTY_STRING
                        }
                        else if (jsonElement.asJsonObject.get("i") is JsonArray) {
                            populateFromJsonObject(jsonElement.asJsonObject, context)
                        }
                    }

                    if (jsonElement.asJsonObject.has("d")) {
                        if (jsonElement.asJsonObject.get("d").isJsonPrimitive && jsonElement.asJsonObject.get("d").asString != null) {
                            sportModelDto.name = jsonElement.asJsonObject.get("d")?.asString ?: EMPTY_STRING
                        }
                        else if (jsonElement.asJsonObject.get("d") is JsonArray) {
                            populateFromJsonObject(jsonElement.asJsonObject, context)
                        }
                    }

                    if (jsonElement.asJsonObject.has("e")) {
                        sportModelDto.activeEvents = context?.deserialize<List<EventModelDto>>(
                            jsonElement.asJsonObject?.get("e"),
                            object : TypeToken<List<EventModelDto>>() {}.type
                        ) ?: emptyList()
                    }

                    //if the id of the dto is empty, do not add it to the list, probably added from the array's json object
                    if (sportModelDto.id.isNotEmpty()) {
                        sportModelDTOsList.add(sportModelDto)
                    }
                }
            }
        } catch(e: Exception) {
            caughtException.value = true
            return sportModelListDTO
        }

        sportModelListDTO.sportModelDTOs = sportModelDTOsList
        return sportModelListDTO
    }

    fun getErrorLiveData(): LiveData<Boolean> = caughtException

    fun resetErrorLiveData() {
        caughtException.value = false
    }

    /**
     * Populates a [SportModelDto] from a JSON object.
     *
     * @param jsonObject    The JSON object to extract data from.
     * @param context The   JSON deserialization context.
     */
    fun populateFromJsonObject(
        jsonObject: JsonObject,
        context: JsonDeserializationContext?
    ) {
        var i = EMPTY_STRING
        var d = EMPTY_STRING
        var e: List<EventModelDto> = arrayListOf()
        for ((key, value) in jsonObject.entrySet()) {
            if (key == "i") {
                if (value is JsonArray) {
                    populateFromJsonArray(value, context)
                }
                else {
                    i = value.asString
                }
            }
            if (key == "d") {
                if (value is JsonArray) {
                    populateFromJsonArray(value, context)
                } else {
                    d = value.asString
                }
            }
            if (key == "e") {
                if (value is JsonArray) {
                    populateFromJsonArray(value, context)
                } else {
                    e = context?.deserialize<List<EventModelDto>>(
                        value,
                        object : TypeToken<List<EventModelDto>>() {}.type
                    ) ?: emptyList()
                }
            }
            sportModelDTOsList.add(SportModelDto(i,d,e))
        }
    }

    /**
     * Populates a [SportModelDto] from a JSON array.
     *
     * @param jsonArray         The JSON array to extract data from.
     * @param context The       JSON deserialization context.
     */
    fun populateFromJsonArray(
        jsonArray: JsonArray,
        context: JsonDeserializationContext?
    ) {
        for (subElement in jsonArray) {
            sportModelDTOsList.add(
                SportModelDto(
                    JsonUtils.getAsStringFromJsonObject("i", subElement.asJsonObject),
                    JsonUtils.getAsStringFromJsonObject("d", subElement.asJsonObject),
                    context?.deserialize<List<EventModelDto>>(subElement.asJsonObject.get("e"), object : TypeToken<List<EventModelDto>>() {}.type) ?: emptyList()
                )
            )
        }
    }
}


