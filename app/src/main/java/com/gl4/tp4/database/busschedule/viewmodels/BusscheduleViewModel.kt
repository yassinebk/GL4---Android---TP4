package com.gl4.tp4.database.busschedule.viewmodels
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.gl4.tp4.database.busschedule.daos.ScheduleDAO
import com.gl4.tp4.database.busschedule.entities.Schedule

class BusScheduleViewModel(private val scheduleDAO: ScheduleDAO): ViewModel() {
    fun fullSchedule(): LiveData<List<Schedule>> = scheduleDAO.getAll()

    fun scheduleForStopName(name: String): LiveData<List<Schedule>> = scheduleDAO.getByStopName(name)
}