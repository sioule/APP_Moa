package com.mobile.moa.auth

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface AuthRetrofitInterface {
    // 회원가입 인증
    /* response_type : code
  client_id : 6344979b-a78d-48c5-97b9-3b4051bdc315
  redirect_uri : http://localhost:8080/authResult
  scope : login inquiry transfer
  state : 12345678123456781234567812345678
  auth_type : 0*/
    @GET("authorize")
    fun certification(
        @Query("response_type") response_type: String,
        @Query("client_id") client_id: String,
        @Query("redirect_uri") redirect_uri: String,
        @Query("scope") scope: String,
        @Query("state") state: String,
        @Query("auth_type") auth_type: Int
//    ): Call<AuthResponse>
    ): Call<ResponseBody>
}