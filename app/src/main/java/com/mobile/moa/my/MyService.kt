package com.mobile.moa.my

import android.util.Log
import com.example.umc_hackathon.getRetrofit
import retrofit2.Call
import retrofit2.Response

class MyService {
    private lateinit var myView: MyView

    fun setMyView(myView: MyView) {
        this.myView = myView
    }

    fun getMyPage(memberId: Long) {
        val getMyService = getRetrofit().create(MyRetrofitInterface::class.java)

        getMyService.getMyPage(memberId).enqueue(object : retrofit2.Callback<MyResponse>{
            override fun onResponse(call: Call<MyResponse>, response: Response<MyResponse>) {
                if (response.isSuccessful) {
                    val my = response.body()!!
                    myView.onGetMySuccess(my)
                    Log.d("my-retrofit", my.id.toString())
                }
            }

            override fun onFailure(call: Call<MyResponse>, t: Throwable) {
                Log.d("my-retrofit-fail", t.toString())
            }

        })
    }
}