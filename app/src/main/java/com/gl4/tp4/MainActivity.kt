package com.gl4.tp4

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gl4.tp4.database.BusScheduleApplication
import com.gl4.tp4.database.ScheduleAdapter
import com.gl4.tp4.database.busschedule.viewmodels.BusScheduleViewModel
import com.gl4.tp4.database.busschedule.viewmodels.BussscheduleViewModelFactory
import java.io.IOException




class MainActivity : AppCompatActivity() {

    private val busScheduleViewModel : BusScheduleViewModel by viewModels() {
        BussscheduleViewModelFactory((application as BusScheduleApplication).database.getScheduleDao())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_layout)
        InitAdapter()
    }

    private fun InitAdapter() {
        val busClassAdapter = ScheduleAdapter { schedule ->
            var intent = Intent(this@MainActivity, DetailsActivity::class.java)
            intent.putExtra("stopName", schedule.stopName)
            startActivity(intent);
        }
        SetupRecycleView(busClassAdapter)
        InitList(busClassAdapter)
    }
    private fun InitList(busClassAdapter: ScheduleAdapter) {
        busScheduleViewModel
            .fullSchedule()
            .observe(this) {
                busClassAdapter.updateList(it)
            }
    }
    private fun SetupRecycleView(busClassAdapter: ScheduleAdapter) {
        val recyclerView: RecyclerView = findViewById(R.id.recycleView)
        recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
        recyclerView.adapter = busClassAdapter
    }

}

