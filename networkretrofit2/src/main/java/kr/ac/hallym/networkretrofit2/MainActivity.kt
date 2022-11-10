package kr.ac.hallym.networkretrofit2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.ac.hallym.networkretrofit2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy{
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}