package com.mobile.moa.auth

import retrofit2.Call
import retrofit2.http.GET

interface AuthRetrofitInterface {
    // 회원가입 인증
    @GET("https://testapi.openbanking.or.kr/oauth/2.0/authorize")
    fun certification(): Call<AuthResponse>
}