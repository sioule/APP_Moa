package com.mobile.moa.mileage

import android.util.Log
import com.example.umc_hackathon.getRetrofit
import retrofit2.Call
import retrofit2.Response
import retrofit2.create

class ShopService {
    private lateinit var shopView: ShopView

    fun setShopView(shopView: ShopView) {
        this.shopView = shopView
    }

    fun getShopList(memberId: Long, jwt: String) {
        val shopService = getRetrofit().create(ShopRetrofitInterface::class.java)

        shopService.getShopList().enqueue(object : retrofit2.Callback<List<ShopResponse>>{
            override fun onResponse(call: Call<List<ShopResponse>>, response: Response<List<ShopResponse>>) {
                if (response.isSuccessful) {
                    val shopList = response.body()!!

                    shopView.onShopListSuccess(shopList)
                    Log.d("shop-list-retrofit", shopList.toString())
                }
            }

            override fun onFailure(call: Call<List<ShopResponse>>, t: Throwable) {
                Log.d("shop-list-retrofit-fail", t.toString())
            }

        })
    }
}