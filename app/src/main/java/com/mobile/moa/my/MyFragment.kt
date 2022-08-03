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

        //사용자 정보 가져오기
        myService.getMyPage(1) //memberId 변경

        //학교 등록하기
        binding.mySchoolBtn.setOnClickListener{
            myService.putSchool(1) //memberId 변경
        }

        //스크랩 리스트 보기
        binding.myScrapBtn.setOnClickListener{
            myService.updateMyPage(1) //memberId 변경
        }

        //목표 관리 페이지
        binding.myGoalBtn.setOnClickListener{
            //goal fragment or activity 연결
        }

        //로그아웃
        binding.logoutTv.setOnClickListener{

        }

        //회원탈퇴
        binding.withdrawTv.setOnClickListener{
            myService.deleteMy(1) //memberId 변경
        }

        return binding.root
    }


    override fun onGetMySuccess(myResponse: MyResponse) {
        binding.myNicknameTv.text = myResponse.nickname
    }

    override fun onPutSchoolSuccess(myResponse: MyResponse) {
        TODO("Not yet implemented")
    }

    override fun onUpdateMySuccess(myResponse: MyResponse) {
        binding.myNicknameTv.text = myResponse.nickname
    }


}