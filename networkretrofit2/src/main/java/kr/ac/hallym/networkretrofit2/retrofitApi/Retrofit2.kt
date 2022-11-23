package kr.ac.hallym.networkretrofit2.retrofitApi

import android.content.Context
import kr.ac.hallym.networkretrofit2.Model.PostPacket
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

//https://jaejong.tistory.com/33
interface Retrofit2{
    @POST("/api/calcSexRatio")
    fun getFunction(
        @Body postBody : PostPacket
    ) : Call<String>


}