package com.ynov.schooltag.course

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.database.*
import com.ynov.schooltag.R
import kotlinx.android.synthetic.main.delays_fragment.*

class CourseFragment : Fragment() {

    var items : Array<Course>? = null
    private lateinit var database: DatabaseReference
    var courses: MutableList<Course> = mutableListOf()


    companion object {

        fun newInstance(): CourseFragment {

            return CourseFragment()
        }
    }


    private fun writeNewCourse(course: Course) {
        val key = database.child("course").push().key
        if(key!=null) course.key = key
        database.child("course").child(course.key).setValue(course)
    }

    fun readFirebaseData(firebaseCallback: FirebaseCallbackCourse) {
        database.child("course").addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                courses.clear()
                dataSnapshot.children.mapNotNullTo(courses) { it.getValue<Course>(Course::class.java) }
                firebaseCallback.onCallback(courses)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.e("loadPost:onCancelled ", "${databaseError.toException()}")
            }
        })
    }
    /*
    fun seedItems(){
        val courseArray = resources.getStringArray(R.array.course)
        for (k in 0..2) {
            if(k==2) items[k] = Delay("", courseArray[k], false)
            else items[k] = Delay("", courseArray[k], true, (k+1*10L))
        }
    }
*/
    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        database = FirebaseDatabase.getInstance().reference
        readFirebaseData(object : FirebaseCallbackCourse {
            override fun onCallback(list: MutableList<Course>) {
                items = courses.toTypedArray()
                recyclerView.adapter = CourseAdapter(items!!)
            }
        })
        return inflater.inflate(R.layout.delays_fragment, container, false)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        recyclerView.layoutManager = LinearLayoutManager(context)

    }

}