package com.ynov.schooltag.user

interface FirebaseCallback {
    fun onCallback(list: MutableList<User>)
}