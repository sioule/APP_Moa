package com.mobile.moa.asset

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.mobile.moa.MainActivity
import com.mobile.moa.auth.AuthResponse
import com.mobile.moa.auth.AuthRetrofitInterface
import com.mobile.moa.auth.AuthService
import com.mobile.moa.auth.AuthView
import com.mobile.moa.databinding.FragmentAssetBinding
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/* written by keh
date: 22.05.30 */

class AssetFragment : Fragment(), AuthView {

    lateinit var binding: FragmentAssetBinding
    private var authService = AuthService()
    private lateinit var myWebView: WebView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAssetBinding.inflate(inflater, container, false)

        if (getToken() != null) {
            val mainActivity = MainActivity()
            mainActivity.changeAssetFragment()
            Log.d("getToken", getToken()!!)
        }
//        changeAssetFragment()

        authService.setCertificationView(this)

        binding.certificationBtn.setOnClickListener{
            binding.certificationBtn.visibility = View.INVISIBLE

            myWebView = binding.authWebView

            myWebView.settings.apply {
                javaScriptEnabled = true
                domStorageEnabled = true
                setSupportMultipleWindows(true)
            }
            //웹뷰 클라이언트 설정
            myWebView.webViewClient = MyWebViewClient()
            myWebView.loadUrl("https://twww.openbanking.or.kr/apt/mobileweb/authorizeNewGW?sessionID=wwff9937135-d287-46a3-8fdf-5bc5c0243ce2&action=Grant&api_tran_id=a197d782-23e2-4b3d-9fd1-f6c80f1664ac&gw_svc_id=faf66bd6cafdf009a37caaac77ba5194&gw_app_key=6344979b-a78d-48c5-97b9-3b4051bdc315&response_type=code&client_id=6344979b-a78d-48c5-97b9-3b4051bdc315&client_info=&redirect_uri=http://localhost:8080/authResult&scope=login+inquiry+transfer&auth_type=0&lang=kor&state=12345678123456781234567812345678")

        }

        return binding.root
    }

    override fun onAuthCertificationSuccess(authResponse: AuthResponse) {
        Log.d("access_token_fragment", authResponse.access_token)
        Log.d("access_token_fragment", authResponse.user_seq_no)
        val spf =  activity?.getSharedPreferences("access_token", Context.MODE_PRIVATE)
        val editor = spf?.edit()

        editor?.putString("seq",authResponse.user_seq_no)
//        editor?.apply()
        editor?.putString("access_token", authResponse.access_token)
        editor?.apply()

        Log.d("access_token_fragment", getToken().toString())

//        val mainActivity = MainActivity()
//        mainActivity.changeAssetFragment()
//        changeAssetFragment()
    }

    override fun onAuthCertificationFailure() {
        Log.d("access_token_fragment", "fail")
    }

    fun authCertification(code: String) {
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

                        onAuthCertificationSuccess(authResponse)
                        Log.d("auth-certification", authResponse.access_token)
                    }
                }

                override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                    onAuthCertificationFailure()
                    Log.d("auth-certif-error", t.toString())
                }
            })
    }

    private fun getToken(): String? {
        val token = activity?.getSharedPreferences("access_token", AppCompatActivity.MODE_PRIVATE)
        return token!!.getString("access_token", null)
        Log.d("token-now", token!!.getString("access_token", null)!!)
    }

    private fun changeAssetFragment() {
        Log.d("change-fragment", "asset")
        val transaction: FragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
        val fragment2 = AssetListFragment()
        transaction.replace(com.mobile.moa.R.id.main_frm, fragment2).commitAllowingStateLoss()
//        activity?.supportFragmentManager?.beginTransaction()?.add(R.id.asset_frm, AssetListFragment())?.commitAllowingStateLoss();
    }

}