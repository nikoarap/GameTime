package com.nikoarap.gametime.utils

import com.google.gson.JsonObject

class JsonUtils {

    companion object {

        fun getAsStringFromJsonObject(elementValue: String, jsonObject: JsonObject?): String {
            return if (jsonObject?.has(elementValue) == true && jsonObject.get(elementValue).isJsonPrimitive && jsonObject.get(elementValue).asString != null) {
                jsonObject.get(elementValue)?.asString ?: Constants.EMPTY_STRING
            } else Constants.EMPTY_STRING
        }

        fun getAsIntFromJsonObject(elementValue: String, jsonObject: JsonObject?): Int {
            return if (jsonObject?.has(elementValue) == true && jsonObject.get(elementValue).isJsonPrimitive) {
                jsonObject.get(elementValue)?.asInt ?: Constants.VALUE_ZERO
            } else Constants.VALUE_ZERO
        }
    }
}