package com.mobile.moa.my

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import android.webkit.WebView
import com.mobile.moa.auth.AuthResponse
import com.mobile.moa.auth.AuthService
import com.mobile.moa.auth.AuthView
import com.mobile.moa.databinding.FragmentMyBinding
import okhttp3.ResponseBody
import kotlin.math.log

/* written by keh
date: 22.05.30 */

class MyFragment : Fragment(), AuthView {

    lateinit var binding: FragmentMyBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMyBinding.inflate(inflater, container, false)

//        binding.certificationBtn.setOnClickListener{
            //authCertification()
//            var url ="https://testapi.openbanking.or.kr/oauth/2.0/authorize?response_type=code&client_id=6344979b-a78d-48c5-97b9-3b4051bdc315&redirect_uri=http://localhost:8080/authResult&scope=login inquiry transfer&state=12345678123456781234567812345678&auth_type=0";
//            val openURL = Intent(android.content.Intent.ACTION_VIEW)
//            openURL.data = Uri.parse(url)
//            startActivity(openURL)

//            val myWebView: WebView = binding.authWebView
//            myWebView.settings.javaScriptEnabled
//            myWebView.settings.domStorageEnabled
            //웹뷰 클라이언트 설정

//            myWebView.loadUrl("https://twww.openbanking.or.kr/apt/mobileweb/authorizeNewGW?sessionID=ff837135-d287-46a3-8fdf-5bc5c0243ce2&action=Grant&api_tran_id=a197d782-23e2-4b3d-9fd1-f6c80f1664ac&gw_svc_id=faf66bd6cafdf009a37caaac77ba5194&gw_app_key=6344979b-a78d-48c5-97b9-3b4051bdc315&response_type=code&client_id=6344979b-a78d-48c5-97b9-3b4051bdc315&client_info=&redirect_uri=http://localhost:8080/authResult&scope=login+inquiry+transfer&auth_type=0&lang=kor&state=12345678123456781234567812345678")

//        }

        binding.mySchoolBtn.setOnClickListener{
            schoolEdit()
        }

        binding.myScrapBtn.setOnClickListener{
            scrapList()
        }

        binding.myGoalBtn.setOnClickListener{
            //goal fragment or activity 연결
        }

        //setOnClickListener() {} 차이 공부
        binding.logoutTv.setOnClickListener{}
        binding.withdrawTv.setOnClickListener{}

        return binding.root
    }

    //학교 등록
    private fun schoolEdit() {

    }

    //마일리지 사용처 스크랩 리스트
    private fun scrapList() {}

    private fun authCertification() {
        val authService = AuthService()
        authService.setCertificationView(this)

        authService.authCertification()
//        val myWebView: WebView = binding.webview
//        myWebView.loadUrl(AuthService().authCertification().toString())
    }

    override fun onAuthCertificationSuccess(authResponse: String) {
//        val myWebView: WebView = binding.webview
//        myWebView.settings.javaScriptEnabled
//        myWebView.loadData(authResponse, "text/html; charset=utf-8", "UTF-8")
//
//        myWebView.loadUrl("https://twww.openbanking.or.kr/apt/mobileweb/authorizeNewGW?sessionID=ff837135-d287-46a3-8fdf-5bc5c0243ce2&action=Grant&api_tran_id=a197d782-23e2-4b3d-9fd1-f6c80f1664ac&gw_svc_id=faf66bd6cafdf009a37caaac77ba5194&gw_app_key=6344979b-a78d-48c5-97b9-3b4051bdc315&response_type=code&client_id=6344979b-a78d-48c5-97b9-3b4051bdc315&client_info=&redirect_uri=http://localhost:8080/authResult&scope=login+inquiry+transfer&auth_type=0&lang=kor&state=12345678123456781234567812345678")
//        Log.d("auth-url", authResponse)
    }

    override fun onAuthCertificationFailure() {
        TODO("Not yet implemented")
    }


}