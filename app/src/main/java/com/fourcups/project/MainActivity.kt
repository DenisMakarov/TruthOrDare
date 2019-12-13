package com.fourcups.project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.login_layout.*
import kotlinx.android.synthetic.main.registration_layout.*
import java.io.OutputStream
import java.net.Socket
import java.nio.charset.Charset
import java.util.*
import kotlin.concurrent.thread



class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


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

            val intent = Intent(this,MainMenu::class.java)
            startActivity(intent)
        }
        login_button.setOnClickListener {
            val log_login:String = login_email.text.toString()
            val log_password:String = login_password.text.toString()

            val intent = Intent(this,MainMenu::class.java)

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

class Client(address: String, port: Int) {
    private val connection: Socket = Socket(address, port)
    private var connected: Boolean = true

    init {
        println("Connected to server at $address on port $port")
    }

    private val reader: Scanner = Scanner(connection.getInputStream())
    private val writer: OutputStream = connection.getOutputStream()

    fun run() {
        thread { read() }
        while (connected) {
            val input = readLine() ?: ""
            if ("exit" in input) {
                connected = false
                reader.close()
                connection.close()
            } else {
                write(input)
            }
        }

    }

    fun write(message: String) {
        writer.write((message + '\n').toByteArray(Charset.defaultCharset()))
    }

    fun read() {
        while (connected)
            println(reader.nextLine())
    }
}