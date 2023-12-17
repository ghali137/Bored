package com.ghali.bored.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Thing(
    @PrimaryKey(autoGenerate = true) val id: Long? = null,
    val text: String,
    var childOf: Long? = null,
    var view: Int = 0,
)

