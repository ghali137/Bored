package com.ghali.bored.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity
data class Thing(
    val text: String,
    @PrimaryKey(autoGenerate = true) val id: Long? = null,
)

@Entity
data class Place(
    val thingId: Long,
    val place: Int,
    var order: Long,
    @PrimaryKey(autoGenerate = true) val id: Long? = null,
)
