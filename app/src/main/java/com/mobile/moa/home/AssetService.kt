package com.mobile.moa.home

import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.mobile.moa.asset.AssetResponse
import com.mobile.moa.auth.AuthResponse
import com.mobile.moa.auth.AuthRetrofitInterface
import com.mobile.moa.mileage.ShopView
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AssetService {

    private lateinit var assetView: AssetView

    fun setAssetView(assetView: AssetView) {
        this.assetView = assetView
    }


    fun total(token: String, seq: String) {
        val interceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val client: OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

        val gson: Gson = GsonBuilder().setLenient().create()

        // baseUrl 안쓰는 부분이라 직접 빌드
        val authService = Retrofit.Builder()
            .baseUrl("https://testapi.openbanking.or.kr/oauth/2.0/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson)).build()
//            .addConverterFactory(ScalarsConverterFactory.create()).build()
            .create(AuthRetrofitInterface::class.java)


        authService.total("Bearer$token", seq)
            .enqueue(object : Callback<AssetResponse> {
                override fun onResponse(call: Call<AssetResponse>, response: Response<AssetResponse>) {
                    if (response.isSuccessful) {
                        val assetResponse = response.body()!!

                        assetView.onGetTotalSuccess(assetResponse)
                        Log.d("auth-certification", assetResponse.toString())
                    }
                }

                override fun onFailure(call: Call<AssetResponse>, t: Throwable) {
//                    assetView()
                    Log.d("auth-certif-error", t.toString())
                }
            })
    }
}