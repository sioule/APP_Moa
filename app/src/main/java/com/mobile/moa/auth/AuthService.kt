package com.mobile.moa.auth

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AuthService {
    fun authCertification() {
        // baseUrl 안쓰는 부분이라 직접 빌드
        val authService = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(AuthRetrofitInterface::class.java)

        authService.certification().enqueue(object : Callback<AuthResponse> {
            override fun onResponse(call: Call<AuthResponse>, response: Response<AuthResponse>) {
                if (response.isSuccessful) {
                    val authResponse: AuthResponse = response.body()!!

                    Log.d("auth-certification", authResponse.toString())

                }
            }

            override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }

}