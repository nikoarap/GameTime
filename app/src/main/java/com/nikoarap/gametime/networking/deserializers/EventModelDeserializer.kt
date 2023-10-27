package com.nikoarap.gametime.networking.deserializers

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.nikoarap.gametime.networking.DTOs.EventModelDTO
import java.lang.reflect.Type

class EventModelDeserializer: JsonDeserializer<EventModelDTO> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): EventModelDTO {
        val jsonObject = json?.asJsonObject
        var d = ""
        var i = ""
        var si = ""
        var tt = 0

        if (jsonObject?.has("d") == true && jsonObject.get("d").isJsonPrimitive && jsonObject.get("d").asString != null) {
            d = jsonObject.get("d")?.asString ?: ""
        }

        if (jsonObject?.has("i") == true && jsonObject.get("i").isJsonPrimitive && jsonObject.get("i").asString != null) {
            i = jsonObject.get("i")?.asString ?: ""
        }

        if (jsonObject?.has("si") == true && jsonObject.get("si").isJsonPrimitive && jsonObject.get("si").asString != null) {
            si = jsonObject.get("si")?.asString ?: ""
        }

        if (jsonObject?.has("tt") == true && jsonObject.get("tt").isJsonPrimitive) {
            tt = jsonObject.get("tt")?.asInt ?: 0
        }
        return EventModelDTO(d, i, si, tt)
    }
}