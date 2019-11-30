package com.fourcups.truthordare

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_age_rate.*

class ageRate : AppCompatActivity() {

    var adult: Boolean = false;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_age_rate)
    }

    fun choosePlace(view:View){
        val placeIntent = Intent(this,Place::class.java)
        adult = (view.id == chooseAdultButton.id)

        placeIntent.putExtra(Place.FOR_ADULT, adult)
        startActivity(placeIntent)
    }

    fun back(view: View){
        val mainIntent = Intent(this,MainActivity::class.java)
        startActivity(mainIntent)
    }
}
