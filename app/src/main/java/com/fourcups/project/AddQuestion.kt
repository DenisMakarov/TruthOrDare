package com.fourcups.project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_add_question.*

var addType:String = "Правда"

class AddQuestion : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_question)

        addType = "Правда"
        back.setOnClickListener{
            val intent = Intent(this,MainMenu::class.java)
            startActivity(intent)
        }

        td.setOnClickListener {
            if (td.text.toString() == "Правда") {
                td.setText("Действие")
                addType = "Действие"
            } else {
                td.setText("Правда")
                addType = "Правда"
            }
        }
        submit.setOnClickListener {
            val servIntent = Intent(this,ConnectionService::class.java)
            servIntent.putExtra("action","add_task")
            if (td.text.toString()=="Правда")
                servIntent.putExtra("flag","0")
            else
                servIntent.putExtra("flag","1")
            servIntent.putExtra("content",content.text.toString())
            startService(servIntent)

            val intent = Intent(this,MainMenu::class.java)
            startActivity(intent)
        }
    }
}
