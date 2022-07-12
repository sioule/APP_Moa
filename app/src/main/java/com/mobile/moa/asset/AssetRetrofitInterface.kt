package com.mobile.moa.asset

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface AssetRetrofitInterface {
    // 프로필 생성
    @GET("asset/goal")
    fun getGoal(@Path("memberId") memberId: Long): Call<Goal>

}