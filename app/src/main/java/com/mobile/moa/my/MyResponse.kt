package com.mobile.moa.my

import com.google.gson.annotations.SerializedName

data class MyResponse (
    @SerializedName("id") val id: Long,
    @SerializedName("nickname") val nickname: String,
    @SerializedName("email") val email: String,
    @SerializedName("password") val password: String,
    @SerializedName("userSchool") val school: String,
    @SerializedName("userResidence") val residence: String // 거주지 String 맞는지 확인 필요
)