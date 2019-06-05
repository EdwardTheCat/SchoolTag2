package com.ynov.schooltag.delay

import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ynov.schooltag.R
import kotlinx.android.synthetic.main.item_delays.view.*

class DelaysAdapter(var items: Array<Delay>) : RecyclerView.Adapter<DelaysAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val lineView = LayoutInflater.from(parent?.context).inflate(R.layout.item_delays, parent, false)
        return ViewHolder(lineView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder?.bindDelays(items[position])
    }

    fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot:
    Boolean = false): View {
        return LayoutInflater.from(context).inflate(layoutRes, this,
            attachToRoot)
    }

    override fun getItemCount(): Int = items.size


    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bindDelays(delay: Delay) {
            with(delay) {
                itemView.course.text= "$course"
                itemView.course.setTextColor(android.support.v4.content.ContextCompat.getColor(view.context, R.color.colorPrimaryDark))
                when(justified){
                    false -> {
                        itemView.imageJustify.setImageResource(R.drawable.ic_assignment_turned_in_black_24dp)
                        itemView.imageJustify.setColorFilter(android.support.v4.content.ContextCompat.getColor(view.context, R.color.colorPrimary))
                    }
                    true ->  {
                        itemView.imageJustify.setImageResource(R.drawable.ic_assignment_late_black_24dp)
                        itemView.imageJustify.setColorFilter(android.support.v4.content.ContextCompat.getColor(view.context, R.color.colorAccent))
                        itemView.duration.setTextColor(android.support.v4.content.ContextCompat.getColor(view.context, R.color.colorAccent))
                    }
                }
                itemView.duration.text = "$duration"+" mn late"
                itemView.lateDate.text = "${lateDate}"
            }
        }
    }
}