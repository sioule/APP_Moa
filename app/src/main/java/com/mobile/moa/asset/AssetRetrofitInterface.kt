package com.mobile.moa.asset

import retrofit2.Call
import retrofit2.http.GET

/*written by keh
date: 22.07.06*/

//서버 연결 후 사용

interface AssetRetrofitInterface {
   @GET("/total")
   fun total(): Call<AssetResponse>
}