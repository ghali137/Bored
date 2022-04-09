package com.ghali.bored.db

import androidx.room.*

@Dao
interface ThingDao {
    @Query("SELECT Thing.* FROM Thing, Place WHERE Thing.id = Place.thingId AND Place.place = :place ORDER BY Place.`order`")
    fun getList(place: Int): List<Thing>

    @Insert
    fun insert(thing: Thing): Long

    @Delete
    fun delete(thing: Thing)
}

@Dao
interface PlaceDao {
    @Insert
    fun insert(place: Place)

    @Update
    fun update(places: List<Place>)

    @Query("SELECT * FROM Place WHERE place = :place " +
            "AND thingId = :id1 OR place = :place AND thingId = :id2")
    fun getSubList(id1: Long, id2: Long, place: Int): List<Place>


    @Query("SELECT MAX(`order`) FROM Place WHERE place = :place")
    fun getHighestOrder(place: Int): Long

    @Query("DELETE FROM Place WHERE thingId = :id")
    fun delete(id: Long)
}