package com.fourcups.project

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import android.os.IBinder
import android.util.Log
import androidx.core.content.ContextCompat.startActivity
import java.io.ByteArrayInputStream
import java.io.InputStream
import java.io.OutputStream
import java.lang.Exception
import java.net.Socket
import java.nio.charset.Charset
import java.util.concurrent.TimeUnit

class ConnectionService : Service() {

    override fun onCreate() {
        super.onCreate()
        Log.d("onCreateTAG","created")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d("onStartCommandTAG","onStartCommand")
        var act = intent!!.getStringExtra("action")

        val ws = WatchSocket()

        if (act == "registration") {
            val reg_login = intent!!.getStringExtra("reg_login")
            val reg_pass = intent!!.getStringExtra("reg_password")
            ws.execute(act,reg_login,reg_pass)
        }else if(act == "login"){
            val log_login = intent!!.getStringExtra("log_login")
            val log_pass = intent!!.getStringExtra("log_password")
            ws.execute(act,log_login,log_pass)
        }else if (act=="add_task"){
            val flag = intent!!.getStringExtra("flag")
            val content = intent!!.getStringExtra("content")
            ws.execute(act,flag,content)
        }else if (act=="next_dare"){
            ws.execute(act)
        }else if (act=="next_truth"){
            ws.execute(act)
        }else if (act=="send_info"){
            val place = intent!!.getStringExtra("location")
            val age = intent!!.getStringExtra("age")
            ws.execute(act,place,age)
        }else if(act=="dislike"){
            ws.execute(act)
        }
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("onDestroTAG","destroyed")
    }

    override fun onBind(intent: Intent): IBinder? {
        Log.d("onBindTAG","Binded")
        return null
    }
}

val charset = Charsets.UTF_8
class WatchSocket : AsyncTask<String, Void, String>() {
    companion object {
        var socketConnected = false
        lateinit var client: Socket
        lateinit var output: OutputStream
        lateinit var input: InputStream
    }
    override fun onPreExecute() {
        super.onPreExecute()
    }

    override fun doInBackground(vararg params: String): String? {
        if (!socketConnected) {
            client = Socket("192.168.1.4", 53211)
            socketConnected = true
            output = client.outputStream
            input = client.inputStream
        }
        if (params[0]=="registration"){
            output.write("[sign_up,${params[1]},${params[2]}]".toByteArray())
            val bteString = ByteArray(100)
            val size = input.read(bteString)
            val stroka = ByteArray(size)
            for (i in 0..size-1)
                stroka[i] = bteString[i]
            Log.d("inputcall",stroka.toString(Charset.defaultCharset()))
            if (stroka.toString(Charset.defaultCharset()) == "False" ||
                stroka.toString(Charset.defaultCharset()) == "Error"){
                Next = false
                Go = true
            }else{
                Next = true
                Go = true
            }
        }else if(params[0]=="login"){
            output.write("[log_in,${params[1]},${params[2]}]".toByteArray())
            val bteString = ByteArray(100)
            val size = input.read(bteString)
            val stroka = ByteArray(size)
            for (i in 0..size-1)
                stroka[i] = bteString[i]
            Log.d("inputcall",stroka.toString(Charset.defaultCharset()))
            if (stroka.toString(Charset.defaultCharset()) == "False" ||
                stroka.toString(Charset.defaultCharset()) == "Error"){
                Next = false
                Go = true
            }else{
                Next = true
                Go = true
            }
        }else if(params[0]=="add_task") {
            output.write("[new_task,${params[1]},${params[2]}]".toByteArray())
            val bteString = ByteArray(512)
            val size = input.read(bteString)

        }else if(params[0]=="next_dare"){
            Log.d("hell1","hello")

            output.write("[next_dare]".toByteArray())
            val bteString = ByteArray(512)
            val size = input.read(bteString)
            val stroka = ByteArray(size)
            for (i in 0..size-1)
                stroka[i] = bteString[i]

            cur_content = stroka.toString(Charset.defaultCharset())
            loaded = true
        }else if(params[0]=="next_truth"){
            Log.d("hell2","hello")

            output.write("[next_quest]".toByteArray())
            val bteString = ByteArray(512)
            val size = input.read(bteString)
            val stroka = ByteArray(size)
            for (i in 0..size-1)
                stroka[i] = bteString[i]

            cur_content = stroka.toString(Charset.defaultCharset())
            loaded = true
        }else if(params[0]=="send_info"){
            output.write("[start,${params[1]},${params[2]}]".toByteArray())
            val bteString = ByteArray(512)
            val size = input.read(bteString)
        }else if(params[0]=="dislike"){
            output.write("[dislike]".toByteArray())
            val bteString = ByteArray(512)
            val size = input.read(bteString)
        }
        return null
    }

    override fun onPostExecute(result: String?) {
        super.onPostExecute(result)
    }

}

