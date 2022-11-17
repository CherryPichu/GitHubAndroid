package kr.ac.hallym.networkretrofit2

import kr.ac.hallym.networkretrofit2.Model.Ticker
import retrofit2.http.GET
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.*
import kotlin.collections.HashMap

interface ApiRequests {

    @FormUrlEncoded
    @POST("/api/calcSexRatio/")
    fun getrequest(
        @FieldMap input : HashMap<String, Object>,
    ) : Call<Ticker>

}