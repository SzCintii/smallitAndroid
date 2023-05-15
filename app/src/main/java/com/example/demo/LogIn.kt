package com.example.demo

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.initialize

class LogIn(_application: Application)  {

        private var application: Application? = null
        private var firebaseAuth: FirebaseAuth? = null

        var userLiveData = MutableLiveData<FirebaseUser?>()
        private var loggedOutLiveData= MutableLiveData<Boolean>()

    init {
            application = _application
            //Firebase.initialize(application!!.applicationContext)
            Firebase.initialize(application!!.applicationContext)
            firebaseAuth = Firebase.auth

        }

    fun login(email: String?, password: String?) {
            firebaseAuth!!.signInWithEmailAndPassword(email!!, password!!)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        userLiveData.postValue(firebaseAuth!!.currentUser)
                    } else {
                        Toast.makeText(application!!.applicationContext, "Login Failure: " +
                                it.exception!!.localizedMessage, Toast.LENGTH_SHORT).show()
                    }
                }
        }






}