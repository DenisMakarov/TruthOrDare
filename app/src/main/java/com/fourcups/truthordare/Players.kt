package com.fourcups.truthordare

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class Players : AppCompatActivity() {

    companion object{
        const val FOR_ADULT ="18+?"
        const val PLACE = ""
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_players)
    }

    fun back(view: View){
        val placeIntent = Intent(this,Place::class.java)
        startActivity(placeIntent)
    }
}
