package com.fourcups.truthordare

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class ageRate : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_age_rate)
    }

    fun choosePlace(view:View){
        val placeIntent = Intent(this,Place::class.java)
        startActivity(placeIntent)
    }

    fun back(view: View){
        val mainIntent = Intent(this,MainActivity::class.java)
        startActivity(mainIntent)
    }
}
