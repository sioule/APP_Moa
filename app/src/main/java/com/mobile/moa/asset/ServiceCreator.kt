package com.mobile.moa.asset

import okhttp3.Interceptor
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

object ServiceCreator {
    private const val BASE_URL = "" // TODO url 설정

    // Retrofit 객체 생성
    val retrofit: Retrofit
        get() = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    // 인터페이스 구현 객체 획득
    var service: AssetRetrofitInterface = retrofit.create(AssetRetrofitInterface::class.java)

    // HEADER에 access token 넣기
    class AppInterceptor : Interceptor {
        var access_token = ""
        var authorization = "Bearer " + access_token
        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain) : Response = with(chain) {
            val newRequest = request().newBuilder()
                .addHeader("Authorization", authorization)
                .build()
            proceed(newRequest)
        }
    }
}