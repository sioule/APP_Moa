package com.mobile.moa.mileage

import com.mobile.moa.model.Shop
import retrofit2.Call
import retrofit2.http.GET

/*written by keh
date: 22.06.24*/

//서버 연결 후 사용

interface ShopRetrofitInterface {
    @GET("shops")
    fun getShops(): Call<ShopResponse>
}