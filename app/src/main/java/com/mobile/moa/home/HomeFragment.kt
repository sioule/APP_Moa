package com.mobile.moa.home

import android.app.Activity
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.core.view.WindowCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobile.moa.databinding.FragmentHomeBinding
import com.mobile.moa.mileage.ShopRVAdapter
import com.mobile.moa.model.Shop

/* written by keh
date: 22.07.04 */

class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    private var shopDatas = ArrayList<Shop>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//       requireActivity().setStatusBarTransparent()

        binding = FragmentHomeBinding.inflate(inflater, container, false)

        //shopDatas가 지금은 비어있음. 서버에서 데이터 가져와서 넣어주는 작업 필요
        val shopRVAdapter = ShopRVAdapter(shopDatas)
        binding.homeMileageRv.adapter = shopRVAdapter
        binding.homeMileageRv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        return binding.root

    }
    
//    상태바 투명 설정 > 지금 nav바도 투명임 수정 예정
//    fun Activity.setStatusBarTransparent() {
//        window.apply {
//            setFlags(
//                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
//                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
//            )
//        }
//        if(Build.VERSION.SDK_INT >= 30) {	// API 30 에 적용
//            WindowCompat.setDecorFitsSystemWindows(window, false)
//        }
//    }



}