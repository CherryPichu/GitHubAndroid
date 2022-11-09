package kr.ac.hallym.service

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.view.View
import kr.ac.hallym.service.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var serviceIntent : Intent

    val binding : ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        binding.ButtonStart.setOnClickListener {
            serviceStart( it );
        }

        binding.ButtonStop.setOnClickListener {
            serviceStop(it);
        }

        binding.ButtonServiceBinder.setOnClickListener {
            serviceBinder(it)
            Log.d("namjung", "$isService")
        }

        binding.ButtonUnBinder.setOnClickListener {
            serviceUnBinder(it)
            Log.d("namjung", "$isService")
        }


        serviceIntent = Intent(this, Service::class.java)



    }

    fun serviceStart(view:View){
        serviceIntent.action = Service.ACTION_CREATE
        startService(serviceIntent)
    }


    fun serviceStop(view:View){
        serviceIntent.action = Service.ACTION_DELETE
//        startService(serviceIntent)
        stopService(serviceIntent)
    }


    var isService = false

    val connection = object :ServiceConnection{
        
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) { // 연결되는 순간 onBinder함수를 호출해서 제어를 넘겨줌.
            isService = true
            Log.d("namjung", "BoundService")
            val binder = service as Service.MyBinder;
            service == binder.getService()
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            isService = false
        }

    }
    lateinit var service : Service

    fun serviceBinder(view : View){
        //
        bindService(serviceIntent, connection , Context.BIND_AUTO_CREATE)
    }

    fun serviceCommand(){
        service.create() // 서비스 객체 제어가 가능.
        service.delete()
    }

    fun serviceUnBinder(view : View){
        stopService(serviceIntent)

    }
}