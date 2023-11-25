package com.gl4.tp4.database
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo

@Entity
data class Schedule (
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "stop_name") val stopName : String,
    @ColumnInfo(name = "arrival_time") val arrivalTime : Int,
    )
