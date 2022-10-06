package com.mobile.moa.auth

import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

class AuthService {
    private lateinit var authView: AuthView

    fun setCertificationView(authView: AuthView) {
        this.authView = authView
    }


    fun authCertification(code: String) {
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


        authService.token(code, "6344979b-a78d-48c5-97b9-3b4051bdc315", "101c7763-e2aa-4ef4-b5b9-d83cf009f50b",
            "http://localhost:8080/authResult", "authorization_code")
            .enqueue(object : Callback<AuthResponse> {
                override fun onResponse(call: Call<AuthResponse>, response: Response<AuthResponse>) {
                    if (response.isSuccessful) {
                        val authResponse = response.body()!!

                        authView.onAuthCertificationSuccess(authResponse)
                        Log.d("auth-certification", authResponse.access_token)
                    }
                }

                override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                    authView.onAuthCertificationFailure()
                    Log.d("auth-certif-error", t.toString())
                }
            })
    }



//    fun authCertification(code: String) {
//        val interceptor = HttpLoggingInterceptor().apply {
//            level = HttpLoggingInterceptor.Level.BODY
//        }
//
//        val client: OkHttpClient = OkHttpClient.Builder()
//            .addInterceptor(interceptor)
//            .build()
//
//        val gson: Gson = GsonBuilder().setLenient().create()
//
//        // baseUrl 안쓰는 부분이라 직접 빌드
//        val authService = Retrofit.Builder()
//            .baseUrl("https://testapi.openbanking.or.kr/oauth/2.0/token")
//            .client(client)
////            .addConverterFactory(GsonConverterFactory.create(gson)).build()
//            .addConverterFactory(ScalarsConverterFactory.create()).build()
//            .create(AuthRetrofitInterface::class.java)
//
////        authService.certification(
////            "code", "6344979b-a78d-48c5-97b9-3b4051bdc315", "http://localhost:8080/authResult",
////        "login inquiry transfer", "12345678123456781234567812345678", 0)
////            .enqueue(object : Callback<AuthResponse> {
////            override fun onResponse(call: Call<AuthResponse>, response: Response<AuthResponse>) {
////                if (response.isSuccessful) {
////                    val authResponse: AuthResponse = response.body()!!
////
////                    authView.onAuthCertificationSuccess(authResponse)
////                    Log.d("auth-certification", authResponse.toString())
////                }
////            }
//        //  code : key 발급 단계에서 받은 authorization_code
////  client_id : 6344979b-a78d-48c5-97b9-3b4051bdc315
////  client_secret : 101c7763-e2aa-4ef4-b5b9-d83cf009f50b
////  redirect_uri : http://localhost:8080/authResult
////  grant_type : authorization_code
//            authService.token(code, "6344979b-a78d-48c5-97b9-3b4051bdc315", "101c7763-e2aa-4ef4-b5b9-d83cf009f50b",
//                            "http://localhost:8080/authResult", "authorization_code")
//            .enqueue(object : Callback<AuthResponse> {
//            override fun onResponse(call: Call<AuthResponse>, response: Response<AuthResponse>) {
//                if (response.isSuccessful) {
//                    val authResponse = response.body()!!
//
//                    authView.onAuthCertificationSuccess(authResponse)
//                    Log.d("auth-certification", authResponse.toString())
//                }
//            }
//
//                override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
//                    Log.d("auth-certif-error", t.toString())
//                }
//
////            override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
////                Log.d("auth-certif-error", t.toString())
////            }
//        })
//    }

}