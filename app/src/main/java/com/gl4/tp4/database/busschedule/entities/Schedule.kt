package com.gl4.tp4.database.busschedule.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Schedule(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "stop_name") val stopName : String,
    @ColumnInfo(name = "arrival_time") val arrivalTime : Int
)