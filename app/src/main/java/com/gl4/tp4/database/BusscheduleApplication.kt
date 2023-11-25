package com.gl4.tp4.database
import AppDatabase
import android.app.Application

class BusScheduleApplication : Application() {
    val database: AppDatabase by lazy{
        AppDatabase.getDatabase(this);
    }
}