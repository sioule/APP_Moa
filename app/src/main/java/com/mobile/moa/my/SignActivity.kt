package com.mobile.moa.my

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.core.view.WindowCompat
import com.mobile.moa.databinding.ActivitySignBinding

/* written by keh
date: 22.07.31
edit: 22.08.02 */

//추후 시작 화면으로 바꾸기
class SignActivity : AppCompatActivity(), SignUpView {

    lateinit var binding: ActivitySignBinding
    lateinit var requestSignUp: RequestSignUp
    lateinit var myService: MyService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setStatusBarTransparent()

        myService.setSignUpView(this)

        binding.signUpBtn.setOnClickListener {
            signUp()
        }
    }

    private fun signUp() {
        requestSignUp.nickname = binding.nicknameEt.text.toString()
        requestSignUp.email = binding.emailEt.text.toString()
        requestSignUp.password = binding.passwordEt.text.toString()

        myService.signUp(requestSignUp)
    }

    private fun setStatusBarTransparent() {
        window.apply {
            setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            )
        }
        if(Build.VERSION.SDK_INT >= 30) {	// API 30 에 적용
            WindowCompat.setDecorFitsSystemWindows(window, false)
        }
    }

    override fun onSignUpSuccess(myResponse: MyResponse) {

    }
}