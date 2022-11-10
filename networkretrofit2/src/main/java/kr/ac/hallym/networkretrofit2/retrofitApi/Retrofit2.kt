package kr.ac.hallym.networkretrofit2.retrofitApi

import android.content.Context
import kr.ac.hallym.networkretrofit2.Model.PostPacket
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

//https://jaejong.tistory.com/33
interface Retrofit2{
    @POST("/calcSexRatio")
    suspend fun getFunction(
        @Query("male") mele : Int,
        @Query("female") female : Int,
    ) : List<PostPacket>

}