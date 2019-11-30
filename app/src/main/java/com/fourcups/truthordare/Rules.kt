package com.fourcups.truthordare

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class Rules : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rules)
    }

    fun back(view: View){
        val menuIntent = Intent(this,MainActivity::class.java)
        startActivity(menuIntent)
    }
}
