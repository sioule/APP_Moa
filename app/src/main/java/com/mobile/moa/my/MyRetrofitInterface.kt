package com.mobile.moa.my

import okhttp3.RequestBody
import retrofit2.http.GET
import retrofit2.http.Path

interface MyRetrofitInterface {
    @GET("/myPage/{id}")
    fun getMyPage(@Path("id") id: Long) : retrofit2.Call<MyResponse>

}