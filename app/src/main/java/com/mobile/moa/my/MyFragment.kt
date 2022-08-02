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
date: 22.05.30
edit : 22.08.02 */

class MyFragment : Fragment(), MyView {

    lateinit var binding: FragmentMyBinding
    lateinit var myService: MyService

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMyBinding.inflate(inflater, container, false)

        myService.setMyView(this)
        myService.getMyPage(1) //memberId 변경


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
        binding.logoutTv.setOnClickListener{

        }
        binding.withdrawTv.setOnClickListener{

        }

        return binding.root
    }

    //학교 등록
    private fun schoolEdit() {

    }

    //마일리지 사용처 스크랩 리스트
    private fun scrapList() {}


    override fun onGetMySuccess(myResponse: MyResponse) {
        binding.myNicknameTv.text = myResponse.nickname
    }


}