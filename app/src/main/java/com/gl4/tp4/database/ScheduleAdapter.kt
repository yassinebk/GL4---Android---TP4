package com.gl4.tp4.database

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gl4.tp4.R
import com.gl4.tp4.database.busschedule.entities.Schedule
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone


class ScheduleAdapter(private val onItemClick: ((Schedule) -> Unit)?) : RecyclerView.Adapter<ScheduleAdapter.ViewHolder>() {
    private var schedules : List<Schedule>  = listOf<Schedule>();

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val stopText: TextView
        val timeText: TextView

        init {
            stopText = view.findViewById(R.id.stop)
            timeText = view.findViewById(R.id.time)
            view.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onItemClick?.let { it1 -> it1(schedules[position]) }
                }
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(viewGroup.context)
            .inflate(R.layout.item_layout, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.stopText.text = schedules.get(position).stopName
        val formattedDate = GetDate(position)
        viewHolder.timeText.text = formattedDate.toString()
    }

    private fun GetDate(position: Int): String {
        val timestamp = schedules.get(position).arrivalTime.toLong() // Your timestamp value

        val date =
            Date(timestamp * 1000) // Convert timestamp to milliseconds and create Date object

        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        sdf.timeZone = TimeZone.getDefault() // Set the desired timezone, if needed

        val formattedDate = sdf.format(date)
        return formattedDate
    }

    fun updateList(dataList: List<Schedule>){
        this.schedules = dataList;
        notifyDataSetChanged();
    }
    override fun getItemCount() = schedules.size
}