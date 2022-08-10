package com.mobile.moa.asset

import android.util.Log
import android.webkit.WebView
import android.webkit.WebViewClient

class MyWebViewClient : WebViewClient() {

//    "code": "2ffd133a-d17a-431d-a6a5",
//  "scope": "login inquiry transfer",
//  "client_info": "test",
//  "state": "b80BLsfigm9OokPTjy03elbJqRHOfGSY"

    override fun onLoadResource(view: WebView?, url: String?) {
        super.onLoadResource(view, url)
        Log.d("onLoadResource", url.toString())
    }
}