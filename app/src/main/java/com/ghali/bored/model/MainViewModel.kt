package com.ghali.bored.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.room.Room
import com.ghali.bored.db.*

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val db = Room.databaseBuilder(
        application.applicationContext,
        ThingDatabase::class.java,
        "thing_database"
    ).allowMainThreadQueries()
        .build()
    private val thingDao = db.thingDao()

    fun getChildrenOf(parent: Long): List<Thing> {
        return thingDao.getList(parent)
    }

    fun insert(thing: Thing) {
        thingDao.insert(thing)
    }

    /*fun swap(id1: Long, id2: Long, title: String) {
        val list: List<Goal> = thingDao.getSubList(id1, id2, title)
        val temp = list[0].order
        list[0].order = list[1].order
        list[1].order = temp
        goalDao.update(list)
    }*/

    fun deleteThing(thing: Thing) {
        thingDao.delete(thing)
    }

}