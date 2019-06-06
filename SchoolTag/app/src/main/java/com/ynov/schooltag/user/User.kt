package com.ynov.schooltag.user

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(var key: String="", var email : String ="", var password : String ="", var firstname : String ="", var lastname : String ="", var cours : String = "Fabino" ) : Parcelable{

    companion object {
        fun create(): User = User()
    }
}