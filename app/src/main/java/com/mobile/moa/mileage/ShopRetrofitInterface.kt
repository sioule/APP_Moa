package com.mobile.moa.mileage

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

/*written by keh
date: 22.06.24*/

//서버 연결 후 사용

interface ShopRetrofitInterface {
    @GET("/store")
    fun getShopList(
        @Header("Authorization") jwt: String
    ): Call<List<ShopResponse>>

    @GET("/store/{memberId}")
    fun getShopListMember(
        @Header("Authorization") jwt: String,
        @Path("memberId") memberId: Long
    ): Call<List<ShopResponse>>

    @POST("/scrap/{memberId}")
    fun addScrap(
        @Header("Authorization") jwt: String,
        @Path("memberId") memberId: Long
    ): Call<ResponseBody>
}