package com.fourcups.project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main_menu.*

class MainMenu : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)

        play_button.setOnClickListener{
            val intent = Intent(this,ChooseMod::class.java)
            startActivity(intent)
        }

        rules.setOnClickListener {
            val intent = Intent(this, Rules::class.java)
            startActivity(intent)
        }
    }
}
