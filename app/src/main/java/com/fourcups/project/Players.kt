package com.fourcups.project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_add_question.*
import kotlinx.android.synthetic.main.activity_players.*
import kotlinx.android.synthetic.main.activity_players.back

var players = arrayOf("Игрок 1","Игрок 2","Игрок 3","Игрок 4","Игрок 5","Игрок 6")
var males = arrayOf(true,true,true,true,true,true)
var playerCount = 2
var curPlayer = 0

class Players : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_players)
        curPlayer = 0

        pl1.setText(players[0])
        pl2.setText(players[1])
        pl3.setText(players[2])
        pl4.setText(players[3])
        pl5.setText(players[4])
        pl6.setText(players[5])
        maleText1.setText(if (males[0]) "М" else "Ж")
        maleText2.setText(if (males[1]) "М" else "Ж")
        maleText3.setText(if (males[2]) "М" else "Ж")
        maleText4.setText(if (males[3]) "М" else "Ж")
        maleText5.setText(if (males[4]) "М" else "Ж")
        maleText6.setText(if (males[5]) "М" else "Ж")

        show()

        back.setOnClickListener{
            val intent = Intent(this,ChoosePlace::class.java)
            playerCount = 2
            startActivity(intent)
        }

        addPlayer.setOnClickListener {
            if (playerCount<6)
                playerCount++
            show()
        }

        start.setOnClickListener {
            val servIntent = Intent(this,ConnectionService::class.java)
            servIntent.putExtra("action","send_info")
            servIntent.putExtra("location", place)
            servIntent.putExtra("age", age.toString())
            startService(servIntent)


            players[0] = pl1.text.toString()
            players[1] = pl2.text.toString()
            if (playerCount>=3){
                players[2] = pl3.text.toString()
            }
            if (playerCount>=4){
                players[3] = pl4.text.toString()
            }
            if (playerCount>=5){
                players[4] = pl5.text.toString()
            }
            if (playerCount>=6){
                players[5] = pl6.text.toString()
            }

            var intent = Intent(this,TurnOf::class.java)
            startActivity(intent)
        }

        sex1.setOnClickListener {
            if (males[0]==true){
                males[0]=false
                maleText1.setText("Ж")
            }else{
                males[0]=true
                maleText1.setText("М")
            }
        }
        sex2.setOnClickListener {
            if (males[1]==true){
                males[1]=false
                maleText2.setText("Ж")
            }else{
                males[1]=true
                maleText2.setText("М")
            }
        }
        sex3.setOnClickListener {
            if (males[2]==true){
                males[2]=false
                maleText3.setText("Ж")
            }else{
                males[2]=true
                maleText3.setText("М")
            }
        }
        sex4.setOnClickListener {
            if (males[3]==true){
                males[3]=false
                maleText4.setText("Ж")
            }else{
                males[3]=true
                maleText4.setText("М")
            }
        }
        sex5.setOnClickListener {
            if (males[4]==true){
                males[4]=false
                maleText5.setText("Ж")
            }else{
                males[4]=true
                maleText5.setText("М")
            }
        }
        sex6.setOnClickListener {
            if (males[5]==true){
                males[5]=false
                maleText6.setText("Ж")
            }else{
                males[5]=true
                maleText6.setText("М")
            }
        }
    }

    fun show(){
        if (playerCount >= 3) {
            pl3.visibility = View.VISIBLE
            sex3.visibility = View.VISIBLE
            rect3.visibility = View.VISIBLE
            maleText3.visibility= View.VISIBLE
        }
        if (playerCount >= 4) {
            pl4.visibility = View.VISIBLE
            sex4.visibility = View.VISIBLE
            rect4.visibility = View.VISIBLE
            maleText4.visibility= View.VISIBLE
        }
        if (playerCount >= 5) {
            pl5.visibility = View.VISIBLE
            sex5.visibility = View.VISIBLE
            rect5.visibility = View.VISIBLE
            maleText5.visibility= View.VISIBLE
        }
        if (playerCount == 6) {
            pl6.visibility = View.VISIBLE
            sex6.visibility = View.VISIBLE
            rect6.visibility = View.VISIBLE
            maleText6.visibility= View.VISIBLE
        }
    }
}
