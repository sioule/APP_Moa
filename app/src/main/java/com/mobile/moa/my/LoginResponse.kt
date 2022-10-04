package com.mobile.moa.my

import com.google.gson.annotations.SerializedName

data class LoginResponse (
    @SerializedName("id") val id: Long,
    @SerializedName("Authorization") val jwt: String
)