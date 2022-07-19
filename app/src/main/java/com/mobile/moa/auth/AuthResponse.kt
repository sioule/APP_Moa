package com.mobile.moa.auth

import com.google.gson.annotations.SerializedName

data class AuthResponse (
//  "code": "2ffd133a-d17a-431d-a6a5",
//  "scope": "login inquiry transfer",
//  "client_info": "test",
//  "state": "b80BLsfigm9OokPTjy03elbJqRHOfGSY"

    @SerializedName("code") val code: String,
    @SerializedName("scope") val scope: String,
    @SerializedName("client_info") val client_info: String,
    @SerializedName("state") val state: String
)