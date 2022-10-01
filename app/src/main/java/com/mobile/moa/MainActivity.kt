package com.mobile.moa

import android.app.Activity
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.core.view.WindowCompat
import com.mobile.moa.asset.AssetFragment
import com.mobile.moa.databinding.ActivityMainBinding
import com.mobile.moa.home.HomeFragment
import com.mobile.moa.mileage.MileageShopFragment
import com.mobile.moa.my.LoginActivity
import com.mobile.moa.my.MyFragment
import com.mobile.moa.pocket_book.PocketBookFragment

/* written by keh
date: 22.06.03 */

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if(getJwt().equals(0)) {
            val intent = Intent(this, LoginActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
        }

        initBottomNavigation()
        setStatusBarTransparent()
    }

    private fun getJwt(): Long {
        val memberId = getSharedPreferences("auth", AppCompatActivity.MODE_PRIVATE)
        return memberId.getLong("jwt", 0)
    }


    private fun initBottomNavigation(){

        supportFragmentManager.beginTransaction()
            .replace(R.id.main_frm, HomeFragment())
            .commitAllowingStateLoss()

        binding.mainBnv.setOnItemSelectedListener{ item ->
            when (item.itemId) {
                R.id.homeFragment -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, HomeFragment())
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }

                R.id.assetFragment -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, AssetFragment())
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }
                R.id.pocketBookFragment -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, PocketBookFragment())
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }
                R.id.mileageShopFragment -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, MileageShopFragment())
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }
                R.id.myFragment -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, MyFragment())
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }
            }
            false
        }
    }

    fun setStatusBarTransparent() {
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
}