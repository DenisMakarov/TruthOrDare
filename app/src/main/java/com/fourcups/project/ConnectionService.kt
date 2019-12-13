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
    lateinit var ws:WatchSocket

    override fun onCreate() {
        super.onCreate()
        ws = WatchSocket()
        Log.d("onCreateTAG","created")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d("onStartCommandTAG","onStartCommand")
        var act = intent!!.getStringExtra("action")
        if (act == "registration") {
            val reg_login = intent!!.getStringExtra("reg_login")
            val reg_pass = intent!!.getStringExtra("reg_password")
            ws.execute(act,reg_login,reg_pass)
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
    var socketConnected = false
    lateinit var client: Socket
    lateinit var output: OutputStream
    lateinit var input: InputStream

    override fun onPreExecute() {
        super.onPreExecute()
    }

    override fun doInBackground(vararg params: String): String? {
        if (!socketConnected) {
            client = Socket("192.168.1.4", 53210)
            output = client.outputStream
            input = client.inputStream
            socketConnected = true
        }
        if (params[0]=="registration"){
            output.write("[sign_up,${params[1]},${params[2]}]".toByteArray())
            val bteString = ByteArray(100)
            val size = input.read(bteString)
            val stroka = ByteArray(size)
            for (i in 0..size-1)
                stroka[i] = bteString[i]
            Log.d("inputcall",stroka.toString(Charset.defaultCharset()))

            registerResult = stroka.toString(Charset.defaultCharset())


        }
        return null
    }

    override fun onPostExecute(result: String?) {
        super.onPostExecute(result)
    }

}

