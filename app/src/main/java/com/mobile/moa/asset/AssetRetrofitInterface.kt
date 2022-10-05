package com.mobile.moa.asset

import com.mobile.moa.model.Account
import com.mobile.moa.model.Balance
import com.mobile.moa.model.UserInfo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

/*written by keh
date: 22.07.06*/

//서버 연결 후 사용

interface AssetRetrofitInterface {
   @GET("/total")
   fun total(): Call<AssetResponse>

   // 사용자 정보 조회 (계좌 정보)
   @GET("https://testapi.openbanking.or.kr/v2.0/user/me")
   fun getUserInfo(
      @Header("access_token") access_token: String,
      @Query("user_seq_no") user_seq_no: String,
   ): Call<UserInfo>

   // 계좌 잔액 조회
   @GET("https://testapi.openbanking.or.kr/oauth/2.0/account/balance/fin_num")
   fun getAccountBalance(
      @Header("access_token") access_token: String,
      @Query("bank_tran_id") bank_tran_id: String, // 은행거래고유번호
      @Query("fintech_use_num") fintech_use_num: String, // 핀테크이용번호
      @Query("tran_dtime") tran_dtime: String // 현재 시간
   ): Call<Balance>

   // 마일리지 조회
   @GET("/mileage/{memberId}")
   fun getMileage(@Path("memberId") memberId: Long): Call<AssetResponse> // TODO response 타입 수정 필요

   // 현금 조회
   @GET("/cash/{memberId}")
   fun getCash(@Path("memberId") memberId: Long): Call<AssetResponse> // TODO response 타입 수정 필요


}