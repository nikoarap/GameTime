package com.nikoarap.gametime.feature_sports.data.cache.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.nikoarap.gametime.feature_sports.data.cache.entity.SportEntity
import com.nikoarap.gametime.utils.Converters

@Database(
    entities = [SportEntity::class],
    version = 1
)

@TypeConverters(Converters::class)
abstract class SportsDatabase: RoomDatabase() {
    abstract val dao: SportsDao
}