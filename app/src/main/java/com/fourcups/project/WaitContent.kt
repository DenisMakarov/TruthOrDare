package com.fourcups.project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class WaitContent : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wait_content)
        if (loaded){
            val intent = Intent(this,Game::class.java)
            startActivity(intent)
        }else{
            val intent = Intent(this,WaitContent::class.java)
            startActivity(intent)
        }
    }
}
