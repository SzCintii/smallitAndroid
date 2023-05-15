package com.example.demo

import android.util.Log
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class TestDataDAO {

    fun getTestData(){
        val db = Firebase.firestore

        db.collection("TestData")

            .get()
            .addOnSuccessListener{

            }
            .addOnFailureListener{ exception ->
                Log.w("debug","Error getting document", exception)
            }

            .addOnCompleteListener {
                Log.w("debug", "Completed")
            }
    }
}