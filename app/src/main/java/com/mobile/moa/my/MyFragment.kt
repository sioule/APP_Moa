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
import com.mobile.moa.mileage.CustomDialog
import com.mobile.moa.mileage.ShopListRAdapter
import com.mobile.moa.mileage.ShopResponse
import okhttp3.ResponseBody
import okhttp3.internal.notify
import kotlin.math.log

/* written by keh
date: 22.05.30
edit : 22.08.02 */

class MyFragment : Fragment(), MyView {

    lateinit var binding: FragmentMyBinding
    private var myService = MyService()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMyBinding.inflate(inflater, container, false)

        myService.setMyView(this)


        //학교 등록하기 - 액티비티 연결
        binding.mySchoolCv.setOnClickListener{
//            myService.putSchool(getJwt(), school)
            val intent = Intent(activity, SchoolActivity::class.java)
//            activity?.startActivity(intent)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
        }

        //스크랩 리스트 보기 - 액티비티 연결
        binding.myScrapCv.setOnClickListener{
//            myService.getMyScrapList(getMemberId(), getJwt()!!)
            val intent = Intent(activity, SchoolActivity::class.java)
//            activity?.startActivity(intent)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
        }

        //회원정보 수정하기
        binding.myUpdateCv.setOnClickListener{
            //수정 페이지 추가
            myService.updateMyPage(getMemberId(), getJwt()!!)
        }

        //목표 관리 페이지
        binding.myGoalCv.setOnClickListener{
            activity?.let{
                val intent = Intent(context, GoalActivity::class.java)
                startActivity(intent)
            }
        }

        //로그아웃
        binding.myLogoutCv.setOnClickListener{

        }

        //회원탈퇴
        binding.withdrawTv.setOnClickListener{
            //다이얼로그 추가
            myService.deleteMy(getMemberId(), getJwt()!!)
        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()
//        notify()
    }

    override fun onStart() {
        super.onStart()

        //사용자 정보 가져오기
        myService.getMyPage(getMemberId(), getJwt()!!)
    }

    private fun getMemberId(): Long {
        val memberId = activity?.getSharedPreferences("auth", AppCompatActivity.MODE_PRIVATE)
        return memberId!!.getLong("memberId", 0)
    }

    private fun getJwt(): String? {
        val jwt = activity?.getSharedPreferences("auth", AppCompatActivity.MODE_PRIVATE)
        return jwt!!.getString("jwt", null)
    }


    override fun onGetMySuccess(myResponse: MyResponse) {
        binding.myNicknameTv.text = myResponse.nickname
        if (myResponse.school != null) {
//            binding.mySchoolTv.visibility = View.VISIBLE
//            binding.mySchoolNameTv.visibility = View.INVISIBLE
            binding.mySchoolTv.visibility = View.INVISIBLE
            binding.mySchoolNameTv.visibility = View.VISIBLE
            binding.mySchoolNameTv.text = myResponse.school
        }
//        else {
//            binding.mySchoolTv.visibility = View.INVISIBLE
//            binding.mySchoolNameTv.visibility = View.VISIBLE
//            binding.mySchoolNameTv.text = myResponse.school
//        }
    }


    override fun onUpdateMySuccess(myResponse: MyResponse) {
        binding.myNicknameTv.text = myResponse.nickname
    }


}