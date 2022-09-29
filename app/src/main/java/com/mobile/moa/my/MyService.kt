package com.mobile.moa.my

import android.util.Log
import com.example.umc_hackathon.getRetrofit
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.mobile.moa.auth.AuthResponse
import com.mobile.moa.auth.AuthRetrofitInterface
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/* written by keh
date: 22.07.31
edit: 22.08.02 */

class MyService {
    private lateinit var myView: MyView
    private lateinit var signUpView: SignUpView
    private lateinit var schoolView: SchoolView

    fun setMyView(myView: MyView) {
        this.myView = myView
    }

    fun setSignUpView(signUpView: SignUpView) {
        this.signUpView = signUpView
    }

    fun setSchoolView(schoolView: SchoolView) {
        this.schoolView = schoolView
    }

    fun signUp(requestSignUp: RequestSignUp) {
        val signUpService = getRetrofit().create(MyRetrofitInterface::class.java)

        signUpService.signUp(requestSignUp).enqueue(object : retrofit2.Callback<MyResponse>{
            override fun onResponse(call: Call<MyResponse>, response: Response<MyResponse>) {
                if (response.isSuccessful) {
                    val my = response.body()!!
                    signUpView.onSignUpSuccess(my)
                    Log.d("my-retrofit", my.id.toString())
                }
            }

            override fun onFailure(call: Call<MyResponse>, t: Throwable) {
                Log.d("my-retrofit-fail", t.toString())
            }

        })
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
        val updateMyService = getRetrofit().create(MyRetrofitInterface::class.java)

        updateMyService.updateMyPage(memberId).enqueue(object : retrofit2.Callback<MyResponse>{
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

    //지도 api 학교 검색
    fun searchSchool(school: String) {
        val interceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val client: OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

        val gson: Gson = GsonBuilder().setLenient().create()

        // baseUrl 안쓰는 부분이라 직접 빌드
        val schoolService = Retrofit.Builder()
            .baseUrl("https://maps.googleapis.com/maps/api/place/findplacefromtext/json")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson)).build()
//            .addConverterFactory(ScalarsConverterFactory.create()).build()
            .create(MyRetrofitInterface::class.java)

        //    &inputtype=textquery
        //    &fields=formatted_address%2Cname%2Crating%2Copening_hours%2Cgeometry&key=YOUR_API_KEY")
//

        schoolService.searchSchool(school, "textquery&fields=formatted_address%2Cname%2Crating%2Copening_hours%2Cgeometry&key=" +
                "AIzaSyAXsUfo4iIKlZ2FqbVipMVl6T_Hevbrhig")
            .enqueue(object : Callback<SchoolResponse> {
                override fun onResponse(call: Call<SchoolResponse>, response: Response<SchoolResponse>) {
                    if (response.isSuccessful) {
                        val schoolResponse = response.body()!!

                        schoolView.onSearchSchoolSuccess(schoolResponse)
                        Log.d("school-search-api", schoolResponse.status)
                    }
                }

                override fun onFailure(call: Call<SchoolResponse>, t: Throwable) {
                    schoolView.onSearchSchoolFail()
                    Log.d("school-search-api", t.message.toString())
                }
            })

    }

    //서버에 학교 전달
    fun putSchool(memberId: Long, school: RequestSchool) {
        val putSchoolService = getRetrofit().create(MyRetrofitInterface::class.java)

        putSchoolService.putSchool(memberId, school).enqueue(object : retrofit2.Callback<MyResponse>{
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
        val deleteMyService = getRetrofit().create(MyRetrofitInterface::class.java)

        deleteMyService.deleteMy(memberId).enqueue(object : retrofit2.Callback<ResponseBody>{
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                Log.d("my-retrofit", response.toString())
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.d("my-retrofit-fail", t.toString())
            }

        })
    }
}