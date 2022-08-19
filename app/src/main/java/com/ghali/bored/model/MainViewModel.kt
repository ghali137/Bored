package com.ghali.bored.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.room.Room
import com.ghali.bored.constants.AWAY
import com.ghali.bored.constants.HOME
import com.ghali.bored.db.*

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val db = Room.databaseBuilder(
        application.applicationContext,
        ThingDatabase::class.java,
        "thing_database"
    ).allowMainThreadQueries().build()
    private val thingDao = db.thingDao()
    private val placeDao = db.placeDao()


    fun addThing(text: String, home: Boolean, away: Boolean) {
        val thing = Thing(text)
        val id = thingDao.insert(thing)
        if (away) {
            val order: Long = placeDao.getHighestOrder(AWAY)+1
            placeDao.insert(Place(id, AWAY, order))
        }
        if (home) {
            val order: Long = placeDao.getHighestOrder(HOME)+1
            placeDao.insert(Place(id, HOME, order))
        }
    }

    fun swap(id1: Long, id2: Long, place: Int) {
        val list: List<Place> = placeDao.getSubList(id1, id2, place)
        val temp = list[0].order
        list[0].order = list[1].order
        list[1].order = temp
        placeDao.update(list)
    }

    fun deleteThing(thing: Thing) {
        thingDao.delete(thing)
        placeDao.delete(thing.id!!)
    }

    fun getList(place: Int): List<Thing> {
        return thingDao.getList(place)
    }
}