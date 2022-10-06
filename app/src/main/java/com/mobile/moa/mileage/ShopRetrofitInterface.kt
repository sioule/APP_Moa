package com.mobile.moa.mileage

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

/*written by keh
date: 22.06.24*/

//서버 연결 후 사용

interface ShopRetrofitInterface {
    @GET("/store")
    fun getShopList(
    ): Call<List<ShopResponse>>

    @GET("/store/{memberId}")
    fun getShopListMember(
        @Path("memberId") memberId: Long
    ): Call<List<ShopResponse>>

    @POST("/scrap/{memberId}")
    fun addScrap(
        @Header("Authorization") jwt: String,
        @Path("memberId") memberId: Long,
        @Body shop: ShopResponse
    ): Call<ResponseBody>
}