package com.ynov.schooltag

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.google.firebase.database.*
import com.ynov.schooltag.user.FirebaseCallback
import com.ynov.schooltag.user.User
import kotlinx.android.synthetic.main.login_activity.*
import org.jetbrains.anko.toast

class LoginActivity : AppCompatActivity()  {

    private lateinit var database: DatabaseReference
    var users: MutableList<User> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)
        database = FirebaseDatabase.getInstance().reference
        AddUser(User("","fabino@gmail.com","fabino", "fabino", "fabino","IOT&Fragment"))
        login_btn.setOnClickListener {
            readFirebaseData(object : FirebaseCallback {
                override fun onCallback(list: MutableList<User>) {
                    var isFound : Boolean = false
                    list.forEach{
                        if (it.email == email_tb.text.toString()){
                            isFound = true
                            if (it.password == password_tb.text.toString()){
                                createMainActivity(it)
                            } else {
                               toast(R.string.bad_password)
                            }
                            return@forEach
                        }
                    }
                    if (isFound == false){
                        toast(R.string.bad_email)
                    }
                }
            })
        }
    }

    private fun AddUser(user:User) {
        val key = database.child("users").push().key
        if(key!=null) user.key = key
        database.child("users").child(user.key).setValue(user)
    }

    fun readFirebaseData(firebaseCallback: FirebaseCallback) {
        database.child("users").addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                users.clear()
                dataSnapshot.children.mapNotNullTo(users) { it.getValue<User>(User::class.java) }
                firebaseCallback.onCallback(users)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.e("loadPost:onCancelled ", "${databaseError.toException()}")
            }
        })
    }

    fun createMainActivity (user: User){
        val intent = Intent(this, MainActivity::class.java).apply {
            putExtra("user", user)
        }
        startActivity(intent)
    }
}