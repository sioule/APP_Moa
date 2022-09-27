package com.mobile.moa.my

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mobile.moa.R

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    private fun saveJwt(jwt: String) {
        val spf = getSharedPreferences("auth", MODE_PRIVATE)
        val editor = spf.edit()

        editor.putLong("jwt", 0)
        editor.apply()
    }
}
