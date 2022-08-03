package com.example.umc_hackathon

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.HttpsURLConnection


const val BASE_URL = "https://" //추후 수정

fun getRetrofit(): Retrofit {
    val interceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    val client: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(interceptor)
//        .hostnameVerifier(
//            HostnameVerifier { hostname, session ->
//                val hv = HttpsURLConnection.getDefaultHostnameVerifier()
//                hv.verify("dev.umcsom.shop", session)
//            })
        .build()

    val gson: Gson = GsonBuilder().setLenient().create()

    val retrofit = Retrofit.Builder().baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(client)
        .build()

    return retrofit

}


