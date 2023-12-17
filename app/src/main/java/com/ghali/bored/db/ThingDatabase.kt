package com.ghali.bored.db

import android.content.Context
import androidx.room.*
import androidx.room.migration.AutoMigrationSpec
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.annotation.NonNull

import androidx.room.Room

import androidx.room.RoomDatabase

import androidx.room.Database




@Database(
    entities = [Thing::class],
    autoMigrations = [
        AutoMigration(from = 7, to = 8, spec = ThingDatabase.MyAutoMigration::class)
    ],
    exportSchema = true,
    version = 8,
)
abstract class ThingDatabase : RoomDatabase() {
    abstract fun thingDao(): ThingDao

    @DeleteColumn(
        tableName = "Thing",
        columnName = "goalId"
    )

    class MyAutoMigration : AutoMigrationSpec {

        @Override
        override fun onPostMigrate(db: SupportSQLiteDatabase) {
            // Invoked once auto migration is save_thing
        }
    }
    companion object {
        private var INSTANCE: ThingDatabase? = null
        @Synchronized
        fun getInstance(context: Context): ThingDatabase? {
            if (INSTANCE == null) {
                INSTANCE = buildDatabase(context)
            }
            return INSTANCE
        }

        private fun buildDatabase(context: Context): ThingDatabase {
            return Room.databaseBuilder(
                context,
                ThingDatabase::class.java,
                "my-database"
            )
                .addCallback(object : Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        getInstance(context)!!.thingDao().insert(Thing(0, "Main Menu"))
                    }
                })
                .build()
        }
    }

}

