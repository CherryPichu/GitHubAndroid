package kr.ac.hallym.thread

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import kr.ac.hallym.thread.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var mThread : Thread;
    private var mCount : Int = 0;
    private val binding by lazy{
        ActivityMainBinding.inflate(layoutInflater)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView( binding.root )

        binding.ButtonStart.setOnClickListener {
            startThread(it)
            true
        }
        binding.ButtonStop.setOnClickListener {
            stopThread(it)
            true
        }


    }


    fun startThread(view : View) {
        if (mThread == null) {
            mThread = object : Thread("My Thread") {
                override fun run(){
                    for(i in 1..100){
                        try{
                            mCount++;
                            Thread.sleep(1000);
                        }catch (e : InterruptedException){
                            break
                        }
                        Log.d("Namjung", "스레드 동작 중 " + mCount)

                    }
                }
            }
            mThread.start()

        }
    }

    fun stopThread(view: View) {
        if(mThread == null){
            (mThread as Thread).stop();
//            mThread = null;
            mCount = 0;
        }
    }
}