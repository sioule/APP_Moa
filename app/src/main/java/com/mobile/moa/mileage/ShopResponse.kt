package com.mobile.moa.mileage

import com.google.gson.annotations.SerializedName

data class ShopResponse (
    @SerializedName("name") val name: String,
    @SerializedName("lat") val lat: String,
    @SerializedName("lng") val lng: String
    )