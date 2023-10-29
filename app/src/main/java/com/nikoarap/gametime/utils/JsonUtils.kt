package com.nikoarap.gametime.utils

import com.google.gson.JsonObject
import com.nikoarap.gametime.utils.Constants.Companion.EMPTY_STRING
import com.nikoarap.gametime.utils.Constants.Companion.VALUE_ZERO_LONG

class JsonUtils {

    companion object {

        fun getAsStringFromJsonObject(elementValue: String, jsonObject: JsonObject?): String {
            return if (jsonObject?.has(elementValue) == true && jsonObject.get(elementValue).isJsonPrimitive && jsonObject.get(elementValue).asString != null) {
                jsonObject.get(elementValue)?.asString ?: EMPTY_STRING
            } else EMPTY_STRING
        }

        fun getAsLongFromJsonObject(elementValue: String, jsonObject: JsonObject?): Long {
            return if (jsonObject?.has(elementValue) == true && jsonObject.get(elementValue).isJsonPrimitive) {
                jsonObject.get(elementValue)?.asLong ?: VALUE_ZERO_LONG
            } else VALUE_ZERO_LONG
        }
    }
}