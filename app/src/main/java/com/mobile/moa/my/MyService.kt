package com.mobile.moa.my

import android.util.Log
import com.example.umc_hackathon.getRetrofit
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.mobile.moa.mileage.ShopResponse
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

/* written by keh
date: 22.07.31
edit: 22.08.02 */

class MyService {
    private lateinit var myView: MyView
    private lateinit var signUpView: SignUpView
    private lateinit var schoolView: SchoolView
    private lateinit var loginView: LoginView
    private lateinit var scrapView: ScrapView

    fun setMyView(myView: MyView) {
        this.myView = myView
    }

    fun setSignUpView(signUpView: SignUpView) {
        this.signUpView = signUpView
    }

    fun setSchoolView(schoolView: SchoolView) {
        this.schoolView = schoolView
    }

    fun setLoginView(loginView: LoginView) {
        this.loginView = loginView
    }

    fun setScrapView(scrapView: ScrapView) {
        this.scrapView = scrapView
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
                Log.d("my-retrofit-sign-fail", t.toString())
            }

        })
    }

    fun login(email: String, password: String) {
        val loginService = getRetrofit().create(MyRetrofitInterface::class.java)

        loginService.login(email, password).enqueue(object : retrofit2.Callback<LoginResponse>{
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    loginView.onLoginSuccess(response.body()!!)
                    Log.d("my-retrofit-login", response.body().toString())
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Log.d("my-retrofit-login-fail", t.toString())
            }

        })
    }

    fun getMyPage(memberId: Long, jwt: String) {
        val getMyService = getRetrofit().create(MyRetrofitInterface::class.java)

        getMyService.getMyPage(jwt, memberId).enqueue(object : retrofit2.Callback<MyResponse>{
            override fun onResponse(call: Call<MyResponse>, response: Response<MyResponse>) {
                if (response.isSuccessful) {
                    val my = response.body()!!
                    myView.onGetMySuccess(my)
                    Log.d("my-retrofit", my.id.toString())
                }
            }

            override fun onFailure(call: Call<MyResponse>, t: Throwable) {
                Log.d("my-retrofit-get-fail", t.toString())
            }

        })
    }

    fun getMyScrapList(memberId: Long, jwt: String) {
        val getMyService = getRetrofit().create(MyRetrofitInterface::class.java)

        getMyService.getMyScrapList(jwt, memberId).enqueue(object : retrofit2.Callback<List<ShopResponse>>{
            override fun onResponse(call: Call<List<ShopResponse>>, response: Response<List<ShopResponse>>) {
                if (response.isSuccessful) {
                    val shopList = response.body()!!

                    scrapView.onGetScrapListSuccess(shopList)
                    Log.d("scrapList-retrofit", shopList.toString())
                }
            }

            override fun onFailure(call: Call<List<ShopResponse>>, t: Throwable) {
                Log.d("scrapList-retrofit-fail", t.toString())
            }
        })
    }

    fun updateMyPage(memberId: Long, jwt: String) {
        val updateMyService = getRetrofit().create(MyRetrofitInterface::class.java)

        updateMyService.updateMyPage(jwt, memberId).enqueue(object : retrofit2.Callback<MyResponse>{
            override fun onResponse(call: Call<MyResponse>, response: Response<MyResponse>) {
                if (response.isSuccessful) {
                    val my = response.body()!!
                    myView.onUpdateMySuccess(my)
                    Log.d("my-retrofit", my.id.toString())
                }
            }

            override fun onFailure(call: Call<MyResponse>, t: Throwable) {
                Log.d("my-retrofit-update-fail", t.toString())
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
            .baseUrl("https://maps.googleapis.com/maps/api/place/findplacefromtext/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson)).build()
//            .addConverterFactory(ScalarsConverterFactory.create()).build()
            .create(MyRetrofitInterface::class.java)

//        https://maps.googleapis.com/maps/api/place/findplacefromtext/json
//  ?fields=formatted_address%2Cname%2Crating%2Copening_hours%2Cgeometry
//  &input=Museum%20of%20Contemporary%20Art%20Australia
//  &inputtype=textquery
//  &key=YOUR_API_KEY

        schoolService.searchSchool("formatted_address,name,rating,opening_hours,geometry",
            school, "textquery", "AIzaSyAHbDljtK9IbzOieHJG7PavB2-YszxJq7s")
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
    fun putSchool(jwt: String, memberId: Long, school: RequestSchool) {
        val putSchoolService = getRetrofit().create(MyRetrofitInterface::class.java)

        putSchoolService.putSchool(jwt, memberId, school).enqueue(object : retrofit2.Callback<MyResponse>{
            override fun onResponse(call: Call<MyResponse>, response: Response<MyResponse>) {
                if (response.isSuccessful) {
                    val my = response.body()!!

                    schoolView.onPutSchoolSuccess(my)
                    Log.d("my-retrofit-school", my.id.toString())
                }
            }

            override fun onFailure(call: Call<MyResponse>, t: Throwable) {
                Log.d("my-retrofit-school-fail", t.toString())
            }

        })
    }

    fun deleteMy(memberId: Long, jwt: String) {
        val deleteMyService = getRetrofit().create(MyRetrofitInterface::class.java)

        deleteMyService.deleteMy(jwt, memberId).enqueue(object : retrofit2.Callback<ResponseBody>{
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                Log.d("my-retrofit-del", response.toString())
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.d("my-retrofit-del-fail", t.toString())
            }

        })
    }
}