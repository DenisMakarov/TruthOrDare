package com.fourcups.project

import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.login_layout.*
import kotlinx.android.synthetic.main.registration_layout.*
import java.io.InputStream
import java.io.OutputStream
import java.net.Socket
import java.nio.charset.Charset
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.concurrent.thread

var Go = false
var Next = false

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Go = false
        Next = false

        showHome()

        back1.setOnClickListener{
            showHome()
        }

        back2.setOnClickListener{
            showHome()
        }

        registration.setOnClickListener {
            showRegistration()
        }

        login.setOnClickListener{
            showLogin()
        }


        reg.setOnClickListener{
            val reg_login:String = email.text.toString()
            val reg_password:String = password.text.toString()

            val servIntent = Intent(this,ConnectionService::class.java)
            servIntent.putExtra("action","registration")
            servIntent.putExtra("reg_login",reg_login)
            servIntent.putExtra("reg_password",reg_password)
            startService(servIntent)

            val intent = Intent(this, WaitActivity::class.java)
            startActivity(intent)
        }

        login_button.setOnClickListener {
            val log_login:String = login_email.text.toString()
            val log_password:String = login_password.text.toString()

            val servIntent = Intent(this,ConnectionService::class.java)
            servIntent.putExtra("action","login")
            servIntent.putExtra("log_login",log_login)
            servIntent.putExtra("log_password",log_password)
            startService(servIntent)

            val intent = Intent(this,WaitActivity::class.java)
            startActivity(intent)
        }
    }

    private fun showRegistration(){
        registration_layout.visibility = View.VISIBLE
        login_layout.visibility=View.GONE
        home_ll.visibility=View.GONE
    }

    private fun showLogin(){
        registration_layout.visibility = View.GONE
        login_layout.visibility=View.VISIBLE
        home_ll.visibility=View.GONE
    }
    private fun showHome(){
        registration_layout.visibility = View.GONE
        login_layout.visibility=View.GONE
        home_ll.visibility=View.VISIBLE
    }
}
