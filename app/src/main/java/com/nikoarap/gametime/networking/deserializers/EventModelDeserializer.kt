package com.nikoarap.gametime.networking.deserializers

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.nikoarap.gametime.networking.DTOs.EventModelDTO
import com.nikoarap.gametime.utils.JsonUtils
import java.lang.reflect.Type

/**
 * A custom JSON deserializer for converting a JSON object to an [EventModelDTO] object.
 * This deserializer is used to parse specific fields from a JSON object and construct an [EventModelDTO] instance.
 */
class EventModelDeserializer: JsonDeserializer<EventModelDTO> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): EventModelDTO {
        return EventModelDTO(
            JsonUtils.getAsStringFromJsonObject("i", json?.asJsonObject),
            JsonUtils.getAsStringFromJsonObject("si", json?.asJsonObject),
            JsonUtils.getAsStringFromJsonObject("d", json?.asJsonObject),
            JsonUtils.getAsLongFromJsonObject("tt", json?.asJsonObject)
        )
    }
}