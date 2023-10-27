package com.nikoarap.gametime.networking.transforming.deserializers

import com.google.gson.JsonArray
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import com.nikoarap.gametime.networking.transforming.DTOs.EventModelDTO
import com.nikoarap.gametime.networking.transforming.DTOs.SportModelDTO
import com.nikoarap.gametime.networking.transforming.DTOs.SportModelListDTO
import java.lang.reflect.Type

class SportModelDeserializer: JsonDeserializer<SportModelListDTO> {

    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): SportModelListDTO {
        val sportModelListDTO = SportModelListDTO()
        val sportModelDTOsList: ArrayList<SportModelDTO> = arrayListOf()
        var d = ""
        var i = ""
        var e : List<EventModelDTO> = arrayListOf()

        if (json is JsonObject) {
            val jsonObject = json.asJsonObject
            if (jsonObject?.has("d") == true && jsonObject.get("d").isJsonPrimitive && jsonObject.get("d").asString != null) {
                d = jsonObject.get("d")?.asString ?: ""
            }

            if (jsonObject?.has("i") == true && jsonObject.get("i").isJsonPrimitive && jsonObject.get("i").asString != null) {
                i = jsonObject.get("i")?.asString ?: ""
            }

            e = context?.deserialize<List<EventModelDTO>>(
                jsonObject?.get("e"),
                object : TypeToken<List<EventModelDTO>>() {}.type
            ) ?: emptyList()

            sportModelDTOsList.add(SportModelDTO(i,d,e))


        } else {
            val jsonArrayObject = json?.asJsonArray
            if (jsonArrayObject != null) {
                for (jsonArrayElement in jsonArrayObject.asJsonArray) {

                    if (jsonArrayElement.asJsonObject.has("d")) {
                        if (jsonArrayElement.asJsonObject.get("d").isJsonPrimitive && jsonArrayElement.asJsonObject.get("d").asString != null) {
                            d = jsonArrayElement.asJsonObject.get("d")?.asString ?: ""
                        } else if (jsonArrayElement.asJsonObject.get("d") is JsonArray) {
                            val jsonObject = jsonArrayElement.asJsonObject

                            for ((key, value) in jsonObject.entrySet()) {

                                if (key == "d") {
                                    if (value is JsonArray) {
                                        val jsonSubArray = value.asJsonArray
                                        for (subElement in jsonSubArray) {
                                            if (subElement.asJsonObject.has("d") && subElement.asJsonObject.get("d").isJsonPrimitive && subElement.asJsonObject.get("d").asString != null) {
                                                d = subElement.asJsonObject.get("d")?.asString ?: ""
                                            }

                                            if (subElement.asJsonObject.has("i") && subElement.asJsonObject.get("i").isJsonPrimitive && subElement.asJsonObject.get("i").asString != null) {
                                                i = subElement.asJsonObject.get("i")?.asString ?: ""
                                            }

                                            e = context?.deserialize<List<EventModelDTO>>(
                                                subElement.asJsonObject.get("e"),
                                                object : TypeToken<List<EventModelDTO>>() {}.type
                                            ) ?: emptyList()

                                            sportModelDTOsList.add(SportModelDTO(i,d,e))
                                        }
                                    } else {
                                        d = value.asString
                                    }
                                }

                                if (key == "i") {
                                    i = value.asString
                                }

                                if (key == "e") {
                                    e = context?.deserialize<List<EventModelDTO>>(
                                        value,
                                        object : TypeToken<List<EventModelDTO>>() {}.type
                                    ) ?: emptyList()
                                }

//                                if (subElement.asJsonObject.has("d") && subElement.asJsonObject.get("d").isJsonPrimitive && subElement.asJsonObject.get("d").asString != null) {
//                                    d = subElement.asJsonObject.get("d")?.asString ?: ""
//                                }
//                                if (subElement.asJsonObject.has("i") && subElement.asJsonObject.get("i").isJsonPrimitive && subElement.asJsonObject.get("i").asString != null) {
//                                    i = subElement.asJsonObject.get("i")?.asString ?: ""
//                                }
//
//                                val e = context?.deserialize<List<EventModelDTO>>(
//                                    subElement.asJsonObject?.get("e"),
//                                    object : TypeToken<List<EventModelDTO>>() {}.type
//                                ) ?: emptyList()

                                sportModelDTOsList.add(SportModelDTO(i,d,e))
                            }

                        }
                    }

                    if (jsonArrayElement.asJsonObject.has("i")) {
                        if (jsonArrayElement.asJsonObject.get("i").isJsonPrimitive && jsonArrayElement.asJsonObject.get("i").asString != null) {
                            i = jsonArrayElement.asJsonObject.get("i")?.asString ?: ""
                        } else {
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
        }










        sportModelListDTO.sportModelDTOs = sportModelDTOsList
        return sportModelListDTO
    }
}


