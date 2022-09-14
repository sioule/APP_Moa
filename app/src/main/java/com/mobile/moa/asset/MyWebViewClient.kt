package com.mobile.moa.asset

import android.util.Log
import android.webkit.WebResourceRequest
import android.webkit.WebResourceResponse
import android.webkit.WebView
import android.webkit.WebViewClient
import com.mobile.moa.auth.AuthResponse
import com.mobile.moa.auth.AuthService
import com.mobile.moa.auth.AuthView
import java.util.*

class MyWebViewClient : WebViewClient(), AuthView{

//    "code": "2ffd133a-d17a-431d-a6a5",
//  "scope": "login inquiry transfer",
//  "client_info": "test",
//  "state": "b80BLsfigm9OokPTjy03elbJqRHOfGSY"

    private var authService: AuthService? = null


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
        Log.d("request-intercept", request?.url?.query.toString())
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
//                code : key 발급 단계에서 받은 authorization_code
//  client_id : 6344979b-a78d-48c5-97b9-3b4051bdc315
//  client_secret : 101c7763-e2aa-4ef4-b5b9-d83cf009f50b
//  redirect_uri : http://localhost:8080/authResult
//  grant_type : authorization_code code.toByteArray()

                authService?.setCertificationView(this)
                authService?.authCertification(c)

//                view!!.postUrl("https://testapi.openbanking.or.kr/oauth/2.0/token", body.toByteArray())
                return true
            }
        }
        Log.d("redirect-boolean", super.shouldOverrideUrlLoading(view, request).toString())
        return super.shouldOverrideUrlLoading(view, request)
    }

    override fun onAuthCertificationSuccess(authResponse: AuthResponse) {
        Log.d("access_token_fragment", authResponse.access_token)
    }

    override fun onAuthCertificationFailure() {

        Log.d("access_token_fragment", "fail")
    }






}