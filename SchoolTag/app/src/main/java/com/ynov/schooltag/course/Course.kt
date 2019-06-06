package com.ynov.schooltag.course

import com.ynov.schooltag.R
import java.util.*


data class Course(var key: String="",
                 var nextCourse : String ="Kotlin",
                 var salle : String= "A4",
                 var courseDate:String="07/07/2019",
                 var startingHours:String="8h30",
                 var endingHours:String="11h",
                 var user : String = "Fabino"
                ){

    companion object {
        fun create(): Course = Course()
    }
}