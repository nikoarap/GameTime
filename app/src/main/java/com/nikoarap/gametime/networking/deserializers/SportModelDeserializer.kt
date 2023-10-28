package com.nikoarap.gametime.networking.deserializers

import com.google.gson.JsonArray
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import com.nikoarap.gametime.networking.DTOs.EventModelDTO
import com.nikoarap.gametime.networking.DTOs.SportModelDTO
import com.nikoarap.gametime.networking.DTOs.SportModelListDTO
import com.nikoarap.gametime.utils.Constants.Companion.EMPTY_STRING
import com.nikoarap.gametime.utils.JsonUtils
import java.lang.reflect.Type

class SportModelDeserializer: JsonDeserializer<SportModelListDTO> {

    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): SportModelListDTO {
        val sportModelListDTO = SportModelListDTO()
        val sportModelDTOsList: ArrayList<SportModelDTO> = arrayListOf()
        val jsonArrayObject = json?.asJsonArray

        if (jsonArrayObject != null) {
            for (jsonElement in jsonArrayObject.asJsonArray) {
                val sportModelDto = SportModelDTO()

                if (jsonElement.asJsonObject.has("i")) {
                    if (jsonElement.asJsonObject.get("i").isJsonPrimitive && jsonElement.asJsonObject.get("i").asString != null) {
                        sportModelDto.id = jsonElement.asJsonObject.get("i")?.asString ?: EMPTY_STRING
                    }
                    else if (jsonElement.asJsonObject.get("i") is JsonArray) {
                        populateFromJsonObject(jsonElement.asJsonObject, context, sportModelDTOsList)
                    }
                }

                if (jsonElement.asJsonObject.has("d")) {
                    if (jsonElement.asJsonObject.get("d").isJsonPrimitive && jsonElement.asJsonObject.get("d").asString != null) {
                        sportModelDto.name = jsonElement.asJsonObject.get("d")?.asString ?: EMPTY_STRING
                    }
                    else if (jsonElement.asJsonObject.get("d") is JsonArray) {
                       populateFromJsonObject(jsonElement.asJsonObject, context, sportModelDTOsList)
                    }
                }

                if (jsonElement.asJsonObject.has("e")) {
                    sportModelDto.activeEvents = context?.deserialize<List<EventModelDTO>>(
                        jsonElement.asJsonObject?.get("e"),
                        object : TypeToken<List<EventModelDTO>>() {}.type
                    ) ?: emptyList()
                }

                //if the id of the dto is empty, do not add it to the list, probably added from the array's json object
                if (sportModelDto.id.isNotEmpty()) {
                    sportModelDTOsList.add(sportModelDto)
                }
            }
        }

        sportModelListDTO.sportModelDTOs = sportModelDTOsList
        return sportModelListDTO
    }

    private fun populateFromJsonObject(
        jsonObject: JsonObject,
        context: JsonDeserializationContext?,
        sportModelDTOsList: ArrayList<SportModelDTO>
    ) {
        var i = EMPTY_STRING
        var d = EMPTY_STRING
        var e: List<EventModelDTO> = arrayListOf()
        for ((key, value) in jsonObject.entrySet()) {
            if (key == "i") {
                if (value is JsonArray) {
                    populateFromJsonArray(value, context, sportModelDTOsList)
                }
                else {
                    i = value.asString
                }
            }
            if (key == "d") {
                if (value is JsonArray) {
                    populateFromJsonArray(value, context, sportModelDTOsList)
                } else {
                    d = value.asString
                }
            }
            if (key == "e") {
                if (value is JsonArray) {
                    populateFromJsonArray(value, context, sportModelDTOsList)
                } else {
                    e = context?.deserialize<List<EventModelDTO>>(
                        value,
                        object : TypeToken<List<EventModelDTO>>() {}.type
                    ) ?: emptyList()
                }
            }
            sportModelDTOsList.add(SportModelDTO(i,d,e))
        }
    }

    private fun populateFromJsonArray(
        jsonArray: JsonArray,
        context: JsonDeserializationContext?,
        sportModelDTOsList: ArrayList<SportModelDTO>
    ) {
        for (subElement in jsonArray) {
            sportModelDTOsList.add(
                SportModelDTO(
                    JsonUtils.getAsStringFromJsonObject("i", subElement.asJsonObject),
                    JsonUtils.getAsStringFromJsonObject("d", subElement.asJsonObject),
                    context?.deserialize<List<EventModelDTO>>(subElement.asJsonObject.get("e"), object : TypeToken<List<EventModelDTO>>() {}.type) ?: emptyList()
                )
            )
        }
    }
}


