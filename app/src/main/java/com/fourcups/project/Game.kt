package com.fourcups.project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_game.*

var cur_content = ""
var loaded = false

class Game : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        playerName.setText(players[curPlayer])
        content.setText(cur_content)

        back.setOnClickListener{
            val intent = Intent(this,TurnOf::class.java)
            startActivity(intent)
        }
        next.setOnClickListener {
            curPlayer= (curPlayer+1) % playerCount
            val intent = Intent(this,TurnOf::class.java)
            startActivity(intent)
        }
        swap.setOnClickListener{
            if (curAct =="dare") {
                val servIntent = Intent(this, ConnectionService::class.java)
                servIntent.putExtra("action", "next_dare")
                startService(servIntent)

                val intent = Intent(this, WaitContent::class.java)
                startActivity(intent)
            }
            if (curAct =="truth") {
                val servIntent = Intent(this, ConnectionService::class.java)
                servIntent.putExtra("action", "next_truth")
                startService(servIntent)

                val intent = Intent(this, WaitContent::class.java)
                startActivity(intent)
            }
        }
        dislike.setOnClickListener{
            val servIntent = Intent(this, ConnectionService::class.java)
            servIntent.putExtra("action", "dislike")
            startService(servIntent)
        }
    }
}
