package com.fourcups.project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_choose_mod.*

var age:Int = 0

class ChooseMod : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_mod)

        back.setOnClickListener{
            val intent = Intent(this,MainMenu::class.java)
            startActivity(intent)
        }
        child.setOnClickListener {
            val intent = Intent(this,ChoosePlace::class.java)
            startActivity(intent)
        }

        adult.setOnClickListener {
            val intent = Intent(this,ChoosePlace::class.java)
            age = 18
            startActivity(intent)
        }
    }
}
