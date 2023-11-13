package com.nikoarap.gametime.feature_sports.data.cache.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nikoarap.gametime.feature_sports.data.cache.entity.SportEntity

@Dao
interface SportsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSportEntities(sports: List<SportEntity>)

    @Query("SELECT * FROM sportentity")
    suspend fun getSportEntities(): List<SportEntity>

}