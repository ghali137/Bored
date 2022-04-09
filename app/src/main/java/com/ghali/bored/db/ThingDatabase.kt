package com.ghali.bored.db

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [Thing::class, Place::class],
    autoMigrations = [AutoMigration (from = 4, to = 5)],
    exportSchema = true,
    version = 5,)
abstract class ThingDatabase : RoomDatabase() {
    abstract fun thingDao(): ThingDao
    abstract fun placeDao(): PlaceDao
}