package com.fourcups.truthordare

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_age_rate.*
import kotlinx.android.synthetic.main.activity_place.*

class Place : AppCompatActivity() {

    var place: String = ""

    companion object{
        const val FOR_ADULT ="18+?"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_place)
    }

    fun back(view: View){
        val ageIntent = Intent(this,ageRate::class.java)
        startActivity(ageIntent)
    }

    fun selectPlayers(view:View){
        val adult: Boolean = intent.getBooleanExtra(FOR_ADULT,false)
        when (view.id){
            chooseCafeButton.id -> place = "Cafe"
            chooseHomeButton.id -> place = "Home"
            chooseStreetButton.id -> place = "Street"
        }
        val playersIntent = Intent(this,Players::class.java)
        playersIntent.putExtra(Players.FOR_ADULT, adult)
        playersIntent.putExtra(Players.PLACE, place)
        startActivity(playersIntent)
    }
}
