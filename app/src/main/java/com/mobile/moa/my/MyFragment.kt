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
import androidx.appcompat.app.AppCompatActivity
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
        myService.getMyPage(getJwt())

        //학교 등록하기
        binding.mySchoolBtn.setOnClickListener{
            myService.putSchool(getJwt())
        }

        //스크랩 리스트 보기
        binding.myScrapBtn.setOnClickListener{
            myService.updateMyPage(getJwt())
        }

        //목표 관리 페이지
        binding.myGoalBtn.setOnClickListener{
            activity?.let{
                val intent = Intent(context, GoalActivity::class.java)
                startActivity(intent)
            }
        }

        //로그아웃
        binding.logoutTv.setOnClickListener{

        }

        //회원탈퇴
        binding.withdrawTv.setOnClickListener{
            myService.deleteMy(getJwt())
        }

        return binding.root
    }

    private fun getJwt(): Long {
        val memberId = this.activity?.getSharedPreferences("auth", AppCompatActivity.MODE_PRIVATE)
        return memberId!!.getLong("jwt", 0)
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