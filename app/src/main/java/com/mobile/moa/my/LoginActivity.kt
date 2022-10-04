package com.mobile.moa.my

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.mobile.moa.R
import com.mobile.moa.databinding.ActivityLoginBinding
import com.mobile.moa.databinding.FragmentMyBinding
import okhttp3.ResponseBody

class LoginActivity : AppCompatActivity(), LoginView {

    lateinit var binding: ActivityLoginBinding
    private var loginService = MyService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginService.setLoginView(this)

        //로그인 서버 연결
        binding.loginSubmitBtn.setOnClickListener{
            val email = binding.loginIdEt.text.toString()
            val pwd = binding.loginPasswordEt.text.toString()
            login(email, pwd)
        }

        //회원가입 페이지 이동
        binding.joinTv.setOnClickListener {
            val intent = Intent(this, SignActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
            finish()
        }
    }

    private fun login(email: String, password: String) {
        loginService.login(email, password)
    }

    private fun saveAuth(memberId: Long, jwt: String) {
        val spf = getSharedPreferences("auth", MODE_PRIVATE)
        val editor = spf.edit()

        editor.putLong("memberId", memberId)
        editor.putString("jwt", jwt)
        editor.apply()
    }


    override fun onLoginSuccess(loginResponse: LoginResponse) {
        Log.d("login-success-activity", loginResponse.toString())
        saveAuth(loginResponse.id, loginResponse.jwt)

    }

    override fun onLoginFailure() {
        Log.d("login-success-activity:", "fail")
    }
}
