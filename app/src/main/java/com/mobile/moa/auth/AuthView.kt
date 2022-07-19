package com.mobile.moa.auth

interface AuthView {
//    fun onAuthCertificationSuccess(authResponse: AuthResponse)
    fun onAuthCertificationSuccess(authResponse: String)
    fun onAuthCertificationFailure()
}