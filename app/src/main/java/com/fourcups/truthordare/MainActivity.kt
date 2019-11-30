package com.fourcups.truthordare

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun play(view: View){
        val ageIntent = Intent(this,ageRate::class.java)
        startActivity(ageIntent)
    }

    fun openRules(view: View){
        val rulesIntent = Intent(this,Rules::class.java)
        startActivity(rulesIntent)
    }
}
