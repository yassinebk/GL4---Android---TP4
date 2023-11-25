package com.gl4.tp4

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gl4.tp4.database.BusScheduleApplication
import com.gl4.tp4.database.ScheduleAdapter
import com.gl4.tp4.database.busschedule.viewmodels.BusScheduleViewModel
import com.gl4.tp4.database.busschedule.viewmodels.BussscheduleViewModelFactory

class DetailsActivity : AppCompatActivity() {
    private val busScheduleViewModel: BusScheduleViewModel by viewModels() {
        BussscheduleViewModelFactory((application as BusScheduleApplication).database.getScheduleDao())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.details_activity)
        val stopName = intent.getStringExtra("stopName")!!
        InitAdapter(stopName)
    }

    private fun InitAdapter(stopName: String) {
        val busClassAdapter = ScheduleAdapter(null)
        SetupRecycleView(busClassAdapter)
        InitList(busClassAdapter, stopName)
    }

    private fun InitList(busClassAdapter: ScheduleAdapter, stopName: String) {
        busScheduleViewModel
            .scheduleForStopName(stopName)
            .observe(this) {
                busClassAdapter.updateList(it)
            }
    }

    private fun SetupRecycleView(busClassAdapter: ScheduleAdapter) {
        val recyclerView: RecyclerView = findViewById(R.id.recycleView)
        recyclerView.layoutManager = LinearLayoutManager(this@DetailsActivity)
        recyclerView.adapter = busClassAdapter
    }
}