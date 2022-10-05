package com.mobile.moa.mileage

import android.util.Log
import com.example.umc_hackathon.getRetrofit
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response

class ShopService {
    private lateinit var shopView: ShopView

    fun setShopView(shopView: ShopView) {
        this.shopView = shopView
    }

    fun getShopList(jwt: String) {
        val shopService = getRetrofit().create(ShopRetrofitInterface::class.java)

        shopService.getShopList(jwt).enqueue(object : retrofit2.Callback<List<ShopResponse>>{
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

    fun getShopListMember(memberId: Long, jwt: String) {
        val shopService = getRetrofit().create(ShopRetrofitInterface::class.java)

        shopService.getShopListMember(jwt, memberId).enqueue(object : retrofit2.Callback<List<ShopResponse>>{
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

    fun addScrap(memberId: Long, jwt: String, shop: ShopResponse) {
        val shopService = getRetrofit().create(ShopRetrofitInterface::class.java)

        shopService.addScrap(jwt, memberId, shop).enqueue(object : retrofit2.Callback<ResponseBody>{
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful) {
                    val scrap = response.body()!!

                    shopView.onAddScrapSuccess()
                    Log.d("add-scrap-success", scrap.toString())
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.d("add-scrap-fail", t.toString())
            }

        })
    }
}