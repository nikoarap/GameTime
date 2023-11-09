package com.nikoarap.gametime

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.gson.JsonArray
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.nikoarap.gametime.data.networking.deserializers.SportModelDeserializer
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertNotNull
import junit.framework.TestCase.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class SportsDeserializerTest {

    @Mock
    private val jsonDeserializationContext: JsonDeserializationContext? = null
    private lateinit var deserializer: SportModelDeserializer

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        deserializer = SportModelDeserializer()
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun testDeserializeHappyPath() {
        // Create a JSON representation for a happy path scenario
        val json = JsonParser().parse(
            """
            [
                {"i": "1", "d": "Football", "e": []},
                {"i": "2", "d": "Basketball", "e": []}
            ]
            """
        )

        val sportModelListDTO = deserializer.deserialize(json, null, jsonDeserializationContext)

        assertNotNull(sportModelListDTO)
        assertEquals(2, sportModelListDTO.sportModelDTOs.size)
        assertEquals("1", sportModelListDTO.sportModelDTOs[0].id)
        assertEquals("Football", sportModelListDTO.sportModelDTOs[0].name)
        assertEquals("2", sportModelListDTO.sportModelDTOs[1].id)
        assertEquals("Basketball", sportModelListDTO.sportModelDTOs[1].name)
        deserializer.getErrorLiveData().value?.let { assertFalse(it) }
    }

    @Test
    fun testDeserializeWithException() {
        val json = JsonParser().parse("invalid_json")
        val sportModelListDTO = deserializer.deserialize(json, null, jsonDeserializationContext)
        assertNotNull(sportModelListDTO)
        deserializer.getErrorLiveData().value?.let { assertTrue(it) }
    }

    @Test
    fun testResetErrorLiveData() {
        deserializer.getErrorLiveData().value?.let { assertTrue(it) }
        deserializer.resetErrorLiveData()
        deserializer.getErrorLiveData().value?.let { assertFalse(it) }
    }

    @Test
    fun testPopulateFromJsonObject() {
        val jsonObject = JsonObject()
        jsonObject.addProperty("i", "3")
        jsonObject.addProperty("d", "Tennis")

        val eventArray = JsonArray()
        eventArray.add(createEventJson("Event1"))
        eventArray.add(createEventJson("Event2"))
        jsonObject.add("e", eventArray)

        deserializer.populateFromJsonObject(jsonObject, jsonDeserializationContext)
        assertEquals(null, deserializer.getErrorLiveData().value)

        val sportModelDTOs = deserializer.sportModelDTOsList
        assertEquals(5, sportModelDTOs.size)
        assertEquals("3", sportModelDTOs[0].id)
        assertEquals("", sportModelDTOs[0].name)
        assertEquals(0, sportModelDTOs[0].activeEvents.size)
    }

    @Test
    fun testPopulateFromJsonArray() {
        val jsonArray = JsonArray()
        val json1 = JsonObject()
        json1.addProperty("i", "4")
        json1.addProperty("d", "Volleyball")
        json1.add("e", createEventArray("Event3"))
        jsonArray.add(json1)

        val json2 = JsonObject()
        json2.addProperty("i", "5")
        json2.addProperty("d", "Cricket")
        json2.add("e", createEventArray("Event4"))
        jsonArray.add(json2)

        deserializer.populateFromJsonArray(jsonArray, jsonDeserializationContext)
        assertEquals(2, deserializer.sportModelDTOsList.size)
        assertEquals("4", deserializer.sportModelDTOsList[0].id)
        assertEquals("Volleyball", deserializer.sportModelDTOsList[0].name)
        assertEquals(0, deserializer.sportModelDTOsList[0].activeEvents.size)
    }

    @Test
    fun testDeserializeWithEmptyId() {
        val jsonString = "[{\"i\":\"\",\"d\":\"Football\",\"e\":[]}]"
        val jsonElement: JsonElement = JsonParser().parse(jsonString)
        val deserializer = SportModelDeserializer()
        val sportModelListDTO = deserializer.deserialize(jsonElement, null, jsonDeserializationContext)
        assertTrue(sportModelListDTO.sportModelDTOs.isEmpty())
    }

    private fun createEventJson(eventName: String): JsonElement {
        val json = JsonObject()
        json.addProperty("name", eventName)
        // Add other properties if needed
        return json
    }

    private fun createEventArray(eventName: String): JsonElement {
        val jsonArray = JsonArray()
        jsonArray.add(createEventJson(eventName))
        // Add more events if needed
        return jsonArray
    }

}