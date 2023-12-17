package com.ghali.bored.db

import androidx.room.*

@Dao
interface ThingDao {
    @Query("SELECT * FROM Thing WHERE childOf = :childOf")
    fun getList(childOf: Long): List<Thing>

    @Insert
    fun insert(thing: Thing)

    @Delete
    fun delete(thing: Thing)
}
