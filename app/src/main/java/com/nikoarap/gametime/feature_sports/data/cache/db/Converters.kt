package com.nikoarap.gametime.feature_sports.data.cache.db

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.reflect.TypeToken
import com.nikoarap.gametime.feature_sports.domain.models.Event

@ProvidedTypeConverter
class Converters(
    private val jsonParser: JsonParser
) {
    @TypeConverter
    fun fromEventsJson(json: String): List<Event> {
        return jsonParser.fromJson<ArrayList<Event>>(
            json,
            object : TypeToken<ArrayList<Event>>(){}.type
        ) ?: emptyList()
    }

    @TypeConverter
    fun toEventsJson(meanings: List<Event>): String {
        return jsonParser.toJson(
            meanings,
            object : TypeToken<ArrayList<Event>>(){}.type
        ) ?: "[]"
    }
}