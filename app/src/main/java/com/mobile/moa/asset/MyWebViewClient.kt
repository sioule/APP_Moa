package com.mobile.moa.asset

import android.util.Log
import android.webkit.WebResourceRequest
import android.webkit.WebResourceResponse
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.mobile.moa.auth.AuthResponse
import com.mobile.moa.auth.AuthRetrofitInterface
import com.mobile.moa.auth.AuthService
import com.mobile.moa.auth.AuthView
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.*

class MyWebViewClient : WebViewClient(){


    private var authService = AuthService()
    private lateinit var authView: AuthView
    private var assetFragment = AssetFragment()

    fun setCertificationView(authView: AuthView) {
        this.authView = authView
    }


    override fun onLoadResource(view: WebView?, url: String?) {
        super.onLoadResource(view, url)
        Log.d("onLoadResource", url.toString())
    }

    override fun onPageFinished(view: WebView?, url: String?) {
        super.onPageFinished(view, url)
    }


    override fun shouldInterceptRequest(
        view: WebView?,
        request: WebResourceRequest?
    ): WebResourceResponse? {
//        Log.d("request-intercept", request?.url?.query.toString())
        return super.shouldInterceptRequest(view, request)
    }

    override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
        if (request != null) {
            Log.d("redirect uri", request.url.toString())
            Log.d("redirect-query", request.url.query.toString() + "~" + request.method)
            if (request.url.query!!.startsWith("code")) {
                Log.d("redirect-boolean-true", super.shouldOverrideUrlLoading(view, request).toString())

                val str: String = request.url.query.toString()
                Log.d("code", str)
                val code = str.split("&")[0]
                val body = code + "&client_id=6344979b-a78d-48c5-97b9-3b4051bdc315" +
                        "&client_secret=101c7763-e2aa-4ef4-b5b9-d83cf009f50b" +
                        "&redirect_uri=http://localhost:8080/authResult&grant_type=authorization_code"
                val c = code.split("=")[1]
                Log.d("body", c)

//                authService?.setCertificationView(this)
                assetFragment.authCertification(c)

//                view!!.postUrl("https://testapi.openbanking.or.kr/oauth/2.0/token", body.toByteArray())
                return true
            }
        }
        Log.d("redirect-boolean", super.shouldOverrideUrlLoading(view, request).toString())
        return super.shouldOverrideUrlLoading(view, request)
    }

//    fun onAuthCertificationSuccess(authResponse: AuthResponse) {
//        Log.d("access_token_fragment", authResponse.access_token)
//
//        val spf = getSharedPreferences("access_token", AppCompatActivity.MODE_PRIVATE)
//        val editor = spf.edit()
//
//        editor.putString("access_token", authResponse.access_token)
//        editor.apply()
//        }
//
//    fun onAuthCertificationFailure() {
//        Log.d("access_token_fragment", "fail")
    }


    private fun authCertification(code: String) {
        val interceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val client: OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

        val gson: Gson = GsonBuilder().setLenient().create()

        // baseUrl 안쓰는 부분이라 직접 빌드
        val authService = Retrofit.Builder()
            .baseUrl("https://testapi.openbanking.or.kr/oauth/2.0/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson)).build()
//            .addConverterFactory(ScalarsConverterFactory.create()).build()
            .create(AuthRetrofitInterface::class.java)


        authService.token(code, "6344979b-a78d-48c5-97b9-3b4051bdc315", "101c7763-e2aa-4ef4-b5b9-d83cf009f50b",
            "http://localhost:8080/authResult", "authorization_code")
            .enqueue(object : Callback<AuthResponse> {
                override fun onResponse(call: Call<AuthResponse>, response: Response<AuthResponse>) {
                    if (response.isSuccessful) {
                        val authResponse = response.body()!!

//                        authView.onAuthCertificationSuccess(authResponse)
                        Log.d("auth-certification", authResponse.access_token)
                    }
                }

                override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
//                    authView.onAuthCertificationFailure()
                    Log.d("auth-certif-error", t.toString())
                }
            })
    }

