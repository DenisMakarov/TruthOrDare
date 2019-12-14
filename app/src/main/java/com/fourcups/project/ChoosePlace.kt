package com.fourcups.project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_choose_place.*

var place : String = ""

class ChoosePlace : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_place)

        back.setOnClickListener{
            val intent = Intent(this, ChooseMod::class.java)
            startActivity(intent)
        }
        house.setOnClickListener {
            place = "home"
            val intent = Intent(this, Players::class.java)
            startActivity(intent)
        }
        street.setOnClickListener {
            place = "street"
            val intent = Intent(this, Players::class.java)
            startActivity(intent)
        }
        cafe.setOnClickListener {
            place = "cafe"
            val intent = Intent(this, Players::class.java)
            startActivity(intent)
        }
    }
}
