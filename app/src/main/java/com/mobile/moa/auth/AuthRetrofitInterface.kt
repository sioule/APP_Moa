package com.mobile.moa.auth

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

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

//    x-www-form-urlencoded
//{
//  code : key 발급 단계에서 받은 authorization_code
//  client_id : 6344979b-a78d-48c5-97b9-3b4051bdc315
//  client_secret : 101c7763-e2aa-4ef4-b5b9-d83cf009f50b
//  redirect_uri : http://localhost:8080/authResult
//  grant_type : authorization_code
//}

    @FormUrlEncoded
    @POST()
    fun token(
        @Field("code") code: String,
        @Field("client_id") client_id: String,
        @Field("client_secret") client_secret: String,
        @Field("redirect_uri") redirect_uri: String,
        @Field("grant_type") grant_type: String
    ): Call<AuthResponse>
//    ): Call<ResponseBody>
}