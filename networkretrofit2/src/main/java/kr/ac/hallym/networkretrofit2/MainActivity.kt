package kr.ac.hallym.networkretrofit2

import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.CalendarView
import android.widget.Toast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kr.ac.hallym.networkretrofit2.Model.PostPacket
import kr.ac.hallym.networkretrofit2.databinding.ActivityMainBinding
import kr.ac.hallym.networkretrofit2.retrofitApi.Retrofit2
import kr.ac.hallym.networkretrofit2.retrofitApi.RetrofitInstance
import kr.ac.hallym.networkretrofit2.retrofitApi.RetrofitInstance.BASE_URL
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import java.util.Objects

class MainActivity : AppCompatActivity() {

    private val binding by lazy{
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val retrofit = RetrofitInstance.getInstance().create(Retrofit2::class.java) // 서비스 객체 생성

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.tvTextView.text = "테스트";

        binding.sendRequest.setOnClickListener {
            apiRequset() //api 호출
        }

    }


    private fun apiRequset(){

        var postBody = PostPacket(23,45);

        retrofit.getFunction(postBody).enqueue(object:  Callback<String>{
            override fun onResponse(call: Call<String>, response: Response<String>) {

                if(response.isSuccessful){
                    // 정상적으로 통신이 성고된 경우
                    var result: String? = response.body()
                    Log.d("namjung", "onResponse 성공: " + result?.toString());
                    binding.tvTimeStamp.setText("결과 : " + result?.toString());
                }else{
                    // 통신이 실패한 경우(응답코드 3xx, 4xx 등)
                    Log.d("namjung", "onResponse 실패")
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                // 통신 실패 (인터넷 끊킴, 예외 발생 등 시스템적인 이유)
                Log.d("namjung", "onFailure 에러: " + t.message.toString());
            }


        })
        

    }



//    private fun apiRequset(){
//        // 1. retroit 객체 생성
//        val retrofit: Retrofit = Retrofit.Builder().baseUrl(BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//
//        // 2. service 객체 생성
//        val apiService : ApiRequests = retrofit.create(ApiRequests::class.java)
//
//        // 3. Call 객체 생성
//        val input : HashMap<String, Object> = HashMap()
//        input.put("male", "30" as Object)
//        input.put("female", "50" as Object)
//        val tickerCall = apiService.getrequest(input)
//
//        // 4. 네트워크 통신
//        tickerCall.enqueue(object : Callback<Ticker> {
//            override fun onResponse(call: Call<Ticker>, response: Response<Ticker>) {
//                val tickerInfo = response.body()
//                Log.d("namjung", "success")
//
//                binding.tvTextView.append("status : ${tickerInfo?.status}")
//                binding.tvTextView.append("status : ${tickerInfo?.data?.result}")
//            }
//
//            override fun onFailure(call: Call<Ticker>, t: Throwable) {
//                // 오류 발생시
//                Log.d("namjung", "fall")
//                call.cancel()
//            }
//        })
//    }

//    private fun getCurrentData() {
//        binding.tvTextView.visibility = View.GONE
//        binding.tvTextView.visibility = View.GONE
//        binding.progressBar.visibility = View.VISIBLE
//
//        val api = Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//            .create(ApiRequests::class.java)
//
//        GlobalScope.launch(Dispatchers.IO) {
//            try {
//                val response = api.getrequest().awaitResponse()
//                if (response.isSuccessful) {
//
//                    val data = response.body()!!
//                    Log.d(TAG, data.toString())
//
//                    withContext(Dispatchers.Main) {
//                        binding.tvTextView.visibility = View.VISIBLE
//                        binding.tvTextView.visibility = View.VISIBLE
//                        binding.progressBar.visibility = View.GONE
////                        binding.tvTimeStamp.text = data.createdAt
//                        binding.tvTextView.text = data.toString()
//
//                    }
//
//                }
//            } catch (e: Exception) {
//                withContext(Dispatchers.Main) {
//                    Toast.makeText(
//                        applicationContext,
//                        "Seems like something went wrong...",
//                        Toast.LENGTH_SHORT
//                    ).show()
//                }
//            }
//        }
}