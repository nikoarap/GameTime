package com.nikoarap.gametime.utils

import com.google.gson.JsonObject
import com.nikoarap.gametime.utils.Constants.EMPTY_STRING
import com.nikoarap.gametime.utils.Constants.VALUE_ZERO_LONG

/**
 * A utility class for JSON manipulation.
 */
class JsonUtils {

    companion object {

        /**
         * Retrieves a String value from a JsonObject based on the specified element.
         *
         * @param elementValue      The name of the element to retrieve.
         * @param jsonObject        The JsonObject from which to retrieve the element.
         * @return                  The retrieved String value or an empty string if the element is not found or is not a String.
         */
        fun getAsStringFromJsonObject(elementValue: String, jsonObject: JsonObject?): String {
            return if (jsonObject?.has(elementValue) == true && jsonObject.get(elementValue).isJsonPrimitive && jsonObject.get(elementValue).asString != null) {
                jsonObject.get(elementValue)?.asString ?: EMPTY_STRING
            } else EMPTY_STRING
        }

        /**
         * Retrieves a Long value from a JsonObject based on the specified element.
         *
         * @param elementValue      The name of the element to retrieve.
         * @param jsonObject        The JsonObject from which to retrieve the element.
         * @return                  The retrieved Long value or 0 if the element is not found or is not a Long.
         */
        fun getAsLongFromJsonObject(elementValue: String, jsonObject: JsonObject?): Long {
            return if (jsonObject?.has(elementValue) == true && jsonObject.get(elementValue).isJsonPrimitive) {
                jsonObject.get(elementValue)?.asLong ?: VALUE_ZERO_LONG
            } else VALUE_ZERO_LONG
        }
    }
}