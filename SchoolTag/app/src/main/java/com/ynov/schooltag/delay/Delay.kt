package com.ynov.schooltag.delay

import com.ynov.schooltag.R
import java.util.*


data class Delay(var key: String="",
                 var course : String ="Course Kotlin",
                 var justified : Boolean = false,
                 var duration : Long =10L,
                 var lateDate:String="",
                 var user : String = "Fabino" ){

    companion object {
        fun create(): Delay = Delay()
    }
}