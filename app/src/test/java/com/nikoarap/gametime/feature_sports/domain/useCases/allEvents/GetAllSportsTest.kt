package com.nikoarap.gametime.feature_sports.domain.useCases.allEvents

import com.nikoarap.gametime.feature_sports.data.repository.MockSportsRepository
import com.nikoarap.gametime.feature_sports.domain.models.Event
import com.nikoarap.gametime.feature_sports.domain.models.Sport
import com.nikoarap.gametime.feature_sports.domain.useCases.GetAllSports
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetAllSportsTest {

    private lateinit var getAllSports: GetAllSports
    private lateinit var mockSportsRepository: MockSportsRepository

    @Before
    fun setup() {
        mockSportsRepository = MockSportsRepository()
        val sports = listOf(
            Sport(
                "FOOT",
                "SOCCER",
                listOf(
                    Event("1", "FOOT", "Arsenal", "Real Madrid", 1703364840),
                    Event("2", "FOOT", "Man City", "Bayern Munich", 1803364840)
                ) ,
                false
            ),
            Sport(
                "BASK",
                "BASKETBALL",
                listOf(
                    Event("1", "BASK", "Panathinaikos", "Fenerbahce", 1703364840),
                ),
                true
            )
        )
        mockSportsRepository.populateSportsFromMockDB(sports)
        getAllSports = GetAllSports(mockSportsRepository)
    }

    @Test
    fun getSportsFromMockRepo_assertSizeEqualsTwo() = runBlocking {
        val sports = getAllSports().first().data
        assert(sports?.size == 2)
    }

    @Test
    fun getSportsFromMockRepo_assertPanathinaikosEventFoundInListEqualsTrue() = runBlocking {
        val sports = getAllSports().first().data
        val panathinaikosEventFoundInList = sports?.flatMap { it.activeEvents }?.find { it.competitorLeft == "Panathinaikos" } != null
        assert(panathinaikosEventFoundInList)
    }
}