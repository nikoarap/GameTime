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
                        d = jsonArrayElement.asJsonObject.get("d")?.asString ?: ""
                    }
                    else if (jsonArrayElement.asJsonObject.get("d") is JsonArray) {
                        val jsonObject = jsonArrayElement.asJsonObject

                        for ((key, value) in jsonObject.entrySet()) {
                            if (key == "d") {
                                if (value is JsonArray) {
                                    val jsonSubArray = value.asJsonArray
                                    for (subElement in jsonSubArray) {
                                        d = JsonUtils.getAsStringFromJsonObject("d", subElement.asJsonObject)
                                        i = JsonUtils.getAsStringFromJsonObject("i", subElement.asJsonObject)
                                        e = context?.deserialize<List<EventModelDTO>>(subElement.asJsonObject.get("e"), object : TypeToken<List<EventModelDTO>>() {}.type) ?: emptyList()
                                        sportModelDTOsList.add(SportModelDTO(i,d,e))
                                    }
                                } else {
                                    d = value.asString
                                }
                            }

                            if (key == "i") {
                                if (value is JsonArray) {
                                    val jsonSubArray = value.asJsonArray
                                    for (subElement in jsonSubArray) {
                                        d = JsonUtils.getAsStringFromJsonObject("d", subElement.asJsonObject)
                                        i = JsonUtils.getAsStringFromJsonObject("i", subElement.asJsonObject)
                                        e = context?.deserialize<List<EventModelDTO>>(subElement.asJsonObject.get("e"), object : TypeToken<List<EventModelDTO>>() {}.type) ?: emptyList()
                                        sportModelDTOsList.add(SportModelDTO(i,d,e))
                                    }
                                }
                                else {
                                    i = value.asString
                                }
                            }

                            if (key == "e") {
                                if (value is JsonArray) {
                                    val jsonSubArray = value.asJsonArray
                                    for (subElement in jsonSubArray) {
                                        d = JsonUtils.getAsStringFromJsonObject("d", subElement.asJsonObject)
                                        i = JsonUtils.getAsStringFromJsonObject("i", subElement.asJsonObject)
                                        e = context?.deserialize<List<EventModelDTO>>(subElement.asJsonObject.get("e"), object : TypeToken<List<EventModelDTO>>() {}.type) ?: emptyList()
                                        sportModelDTOsList.add(SportModelDTO(i,d,e))
                                    }
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
                }

                if (jsonArrayElement.asJsonObject.has("i")) {
                    if (jsonArrayElement.asJsonObject.get("i").isJsonPrimitive && jsonArrayElement.asJsonObject.get("i").asString != null) {
                        i = jsonArrayElement.asJsonObject.get("i")?.asString ?: ""
                    }
                    else {
                        val jsonSubArray = jsonArrayElement.asJsonObject.asJsonArray
                        for (subElement in jsonSubArray) {
                            if (subElement.asJsonObject.has("d") && subElement.asJsonObject.get("d").isJsonPrimitive && subElement.asJsonObject.get("d").asString != null) {
                                d = subElement.asJsonObject.get("d")?.asString ?: ""
                            }
                            if (subElement.asJsonObject.has("i") && subElement.asJsonObject.get("i").isJsonPrimitive && subElement.asJsonObject.get("i").asString != null) {
                                i = subElement.asJsonObject.get("i")?.asString ?: ""
                            }

                            e = context?.deserialize<List<EventModelDTO>>(
                                subElement.asJsonObject?.get("e"),
                                object : TypeToken<List<EventModelDTO>>() {}.type
                            ) ?: emptyList()

                            sportModelDTOsList.add(SportModelDTO(i,d,e))
                        }
                    }
                }

                e = context?.deserialize<List<EventModelDTO>>(
                    jsonArrayElement.asJsonObject?.get("e"),
                    object : TypeToken<List<EventModelDTO>>() {}.type
                ) ?: emptyList()


                sportModelDTOsList.add(SportModelDTO(i,d,e))
            }
        }

        sportModelListDTO.sportModelDTOs = sportModelDTOsList
        return sportModelListDTO
    }
}


