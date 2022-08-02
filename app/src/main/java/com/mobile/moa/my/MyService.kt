package com.mobile.moa.my

import android.util.Log
import com.example.umc_hackathon.getRetrofit
import okhttp3.ResponseBody
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

    fun updateMyPage(memberId: Long) {
        val getMyService = getRetrofit().create(MyRetrofitInterface::class.java)

        getMyService.updateMyPage(memberId).enqueue(object : retrofit2.Callback<MyResponse>{
            override fun onResponse(call: Call<MyResponse>, response: Response<MyResponse>) {
                if (response.isSuccessful) {
                    val my = response.body()!!
                    myView.onUpdateMySuccess(my)
                    Log.d("my-retrofit", my.id.toString())
                }
            }

            override fun onFailure(call: Call<MyResponse>, t: Throwable) {
                Log.d("my-retrofit-fail", t.toString())
            }

        })
    }

    fun putSchool(memberId: Long) {
        val getMyService = getRetrofit().create(MyRetrofitInterface::class.java)

        getMyService.putSchool(memberId).enqueue(object : retrofit2.Callback<MyResponse>{
            override fun onResponse(call: Call<MyResponse>, response: Response<MyResponse>) {
                if (response.isSuccessful) {
                    val my = response.body()!!
                    myView.onPutSchoolSuccess(my)
                    Log.d("my-retrofit", my.id.toString())
                }
            }

            override fun onFailure(call: Call<MyResponse>, t: Throwable) {
                Log.d("my-retrofit-fail", t.toString())
            }

        })
    }

    fun deleteMy(memberId: Long) {
        val getMyService = getRetrofit().create(MyRetrofitInterface::class.java)

        getMyService.deleteMy(memberId).enqueue(object : retrofit2.Callback<ResponseBody>{
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                Log.d("my-retrofit", response.toString())
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.d("my-retrofit-fail", t.toString())
            }

        })
    }
}