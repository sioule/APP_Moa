package com.mobile.moa.my

import com.mobile.moa.asset.AssetRetrofitInterface
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceCreator {
    private const val BASE_URL = "http://15.164.199.153" // TODO url 설정

    // Retrofit 객체 생성
    val retrofit: Retrofit
        get() = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    // 인터페이스 구현 객체 획득
    var service: GoalRetrofitInterface = retrofit.create(GoalRetrofitInterface::class.java)

}