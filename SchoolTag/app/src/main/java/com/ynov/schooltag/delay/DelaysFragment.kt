package com.ynov.schooltag.delay

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

class DelaysFragment : Fragment() {

    var items : Array<Delay>? = null
    private lateinit var database: DatabaseReference
    var delays: MutableList<Delay> = mutableListOf()


    companion object {

        fun newInstance(): DelaysFragment {
            return DelaysFragment()
        }
    }

    private fun writeNewDelay(delay:Delay) {
        val key = database.child("delays").push().key
        if(key!=null) delay.key = key
        database.child("delays").child(delay.key).setValue(delay)
    }

    fun readFirebaseData(firebaseCallback: FirebaseCallback) {
        database.child("delays").addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                delays.clear()
                dataSnapshot.children.mapNotNullTo(delays) { it.getValue<Delay>(Delay::class.java) }
                firebaseCallback.onCallback(delays)
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
        //seedItems()
        database = FirebaseDatabase.getInstance().reference
        readFirebaseData(object : FirebaseCallback {
            override fun onCallback(list: MutableList<Delay>) {
                items = delays.toTypedArray()
                recyclerView.adapter = DelaysAdapter(items!!)
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