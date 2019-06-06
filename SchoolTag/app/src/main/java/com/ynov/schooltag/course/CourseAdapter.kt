package com.ynov.schooltag.course

import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ynov.schooltag.R
import kotlinx.android.synthetic.main.item_course.view.*
import kotlinx.android.synthetic.main.item_delays.view.*

class CourseAdapter(var items: Array<Course>) : RecyclerView.Adapter<CourseAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val lineView = LayoutInflater.from(parent.context).inflate(R.layout.item_course, parent, false)
        return ViewHolder(lineView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.getCourse(items[position])
    }

    override fun getItemCount(): Int = items.size

    fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot:
    Boolean = false): View {
        return LayoutInflater.from(context).inflate(layoutRes, this,
            attachToRoot)
    }

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun getCourse(course: Course) {
            with(course) {
                itemView.courseDate.text= "$courseDate"
                itemView.startingHours.text = "$startingHours - $endingHours"
                itemView.nextCourse.text = "$nextCourse"
                itemView.nextCourse.setTextColor(android.support.v4.content.ContextCompat.getColor(view.context, R.color.colorPrimaryDark))
                itemView.salle.text = "$salle"
                itemView.salle.setTextColor(android.support.v4.content.ContextCompat.getColor(view.context, R.color.colorAccent))
            }
        }
    }

}