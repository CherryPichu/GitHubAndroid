package kr.ac.hallym.service

import android.app.Service
import android.content.Intent
import android.content.Intent.ACTION_DELETE
import android.os.Binder
import android.os.IBinder
import android.util.Log
import java.nio.file.Files.delete

class Service : Service() {

    companion object{
        val ACTION_CREATE = "create"
        val ACTION_DELETE = "delete"
    }

    inner class MyBinder : Binder(){
        fun getService(): Service{

            return this@Service
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        //Intent에 의도를 담아서 전달.
        val action = intent?.action
        when(action){
            ACTION_CREATE -> create()
            ACTION_DELETE -> delete()
        }


        return super.onStartCommand(intent, flags, startId)
    }


    override fun onBind(intent: Intent?): IBinder? {
        return MyBinder();
    }

    fun create(){
        Log.d("namjung", "create()가 호출됨")
    }
    fun delete(){
        Log.d("namjung", "delete()가 호출됨")
    }


}