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
        var d = EMPTY_STRING
        var i = EMPTY_STRING
        var e : List<EventModelDTO> = arrayListOf()

        val jsonArrayObject = json?.asJsonArray
        if (jsonArrayObject != null) {
            for (jsonArrayElement in jsonArrayObject.asJsonArray) {

                if (jsonArrayElement.asJsonObject.has("d")) {
                    if (jsonArrayElement.asJsonObject.get("d").isJsonPrimitive && jsonArrayElement.asJsonObject.get("d").asString != null) {
                        d = jsonArrayElement.asJsonObject.get("d")?.asString ?: EMPTY_STRING
                    }
                    else if (jsonArrayElement.asJsonObject.get("d") is JsonArray) {
                       addFromJsonObject(jsonArrayElement.asJsonObject, context, sportModelDTOsList)
                    }
                }

                if (jsonArrayElement.asJsonObject.has("i")) {
                    if (jsonArrayElement.asJsonObject.get("i").isJsonPrimitive && jsonArrayElement.asJsonObject.get("i").asString != null) {
                        i = jsonArrayElement.asJsonObject.get("i")?.asString ?: ""
                    }
                    else if (jsonArrayElement.asJsonObject.get("i") is JsonArray) {
                        addFromJsonObject(jsonArrayElement.asJsonObject, context, sportModelDTOsList)
                    }
                }

                if (jsonArrayElement.asJsonObject.has("e")) {
                    e = context?.deserialize<List<EventModelDTO>>(
                        jsonArrayElement.asJsonObject?.get("e"),
                        object : TypeToken<List<EventModelDTO>>() {}.type
                    ) ?: emptyList()
                }

                sportModelDTOsList.add(SportModelDTO(i,d,e))
            }
        }

        sportModelListDTO.sportModelDTOs = sportModelDTOsList
        return sportModelListDTO
    }

    private fun addFromJsonObject(
        jsonObject: JsonObject,
        context: JsonDeserializationContext?,
        sportModelDTOsList: ArrayList<SportModelDTO>
    ) {
        var i = EMPTY_STRING
        var d = EMPTY_STRING
        var e: List<EventModelDTO> = arrayListOf()
        for ((key, value) in jsonObject.entrySet()) {
            if (key == "d") {
                if (value is JsonArray) {
                    addFromJsonArray(value, context, sportModelDTOsList)
                } else {
                    d = value.asString
                }
            }
            if (key == "i") {
                if (value is JsonArray) {
                    addFromJsonArray(value, context, sportModelDTOsList)
                }
                else {
                    i = value.asString
                }
            }
            if (key == "e") {
                if (value is JsonArray) {
                    addFromJsonArray(value, context, sportModelDTOsList)
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

    private fun addFromJsonArray(
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


