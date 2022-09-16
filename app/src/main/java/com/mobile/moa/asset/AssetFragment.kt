package com.mobile.moa.asset

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import com.mobile.moa.auth.AuthResponse
import com.mobile.moa.auth.AuthView
import com.mobile.moa.databinding.FragmentAssetBinding

/* written by keh
date: 22.05.30 */

class AssetFragment : Fragment(), AuthView {

    lateinit var binding: FragmentAssetBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAssetBinding.inflate(inflater, container, false)

        binding.certificationBtn.setOnClickListener{
            //authCertification()
//            var url ="https://testapi.openbanking.or.kr/oauth/2.0/authorize?response_type=code&client_id=6344979b-a78d-48c5-97b9-3b4051bdc315&redirect_uri=http://localhost:8080/authResult&scope=login inquiry transfer&state=12345678123456781234567812345678&auth_type=0";
//            val openURL = Intent(android.content.Intent.ACTION_VIEW)
//            openURL.data = Uri.parse(url)
//            startActivity(openURL)



            val myWebView: WebView = binding.authWebView
            myWebView.settings.apply {
                javaScriptEnabled = true
                domStorageEnabled = true
                setSupportMultipleWindows(true)
            }
            //웹뷰 클라이언트 설정
            myWebView.webViewClient = MyWebViewClient()
            myWebView.loadUrl("https://twww.openbanking.or.kr/apt/mobileweb/authorizeNewGW?sessionID=ffff937135-d287-46a3-8fdf-5bc5c0243ce2&action=Grant&api_tran_id=a197d782-23e2-4b3d-9fd1-f6c80f1664ac&gw_svc_id=faf66bd6cafdf009a37caaac77ba5194&gw_app_key=6344979b-a78d-48c5-97b9-3b4051bdc315&response_type=code&client_id=6344979b-a78d-48c5-97b9-3b4051bdc315&client_info=&redirect_uri=http://localhost:8080/authResult&scope=login+inquiry+transfer&auth_type=0&lang=kor&state=12345678123456781234567812345678")


        }

        return binding.root
    }

    override fun onAuthCertificationSuccess(authResponse: AuthResponse) {
        Log.d("access_token_fragment", authResponse.access_token)
    }

    override fun onAuthCertificationFailure() {
        TODO("Not yet implemented")
    }


//    private fun authCertification() {
//        val authService = AuthService()
//        authService.setCertificationView(this)

//        authService.authCertification()
//        val myWebView: WebView = binding.webview
//        myWebView.loadUrl(AuthService().authCertification().toString())
//    }

//    override fun onAuthCertificationSuccess(authResponse: String) {
//        val myWebView: WebView = binding.webview
//        myWebView.settings.javaScriptEnabled
//        myWebView.loadData(authResponse, "text/html; charset=utf-8", "UTF-8")
//
//        myWebView.loadUrl("https://twww.openbanking.or.kr/apt/mobileweb/authorizeNewGW?sessionID=ff837135-d287-46a3-8fdf-5bc5c0243ce2&action=Grant&api_tran_id=a197d782-23e2-4b3d-9fd1-f6c80f1664ac&gw_svc_id=faf66bd6cafdf009a37caaac77ba5194&gw_app_key=6344979b-a78d-48c5-97b9-3b4051bdc315&response_type=code&client_id=6344979b-a78d-48c5-97b9-3b4051bdc315&client_info=&redirect_uri=http://localhost:8080/authResult&scope=login+inquiry+transfer&auth_type=0&lang=kor&state=12345678123456781234567812345678")
//        Log.d("auth-url", authResponse)
//    }


}