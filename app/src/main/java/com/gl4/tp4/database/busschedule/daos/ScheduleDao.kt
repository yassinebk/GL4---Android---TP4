package com.gl4.tp4.database.busschedule.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.gl4.tp4.database.busschedule.entities.Schedule

@Dao
interface ScheduleDAO {
    @Query("Select * from Schedule order by Arrival_time")
    public fun getAll(): LiveData<List<Schedule>>

    @Query("Select * from Schedule where Stop_name like :stopName")
    public fun getByStopName(stopName: String) : LiveData<List<Schedule>>
}