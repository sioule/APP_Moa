package com.mobile.moa.my

import okhttp3.ResponseBody

interface LoginView {
    fun onLoginSuccess(loginResponse: LoginResponse)
    fun onLoginFailure()
}