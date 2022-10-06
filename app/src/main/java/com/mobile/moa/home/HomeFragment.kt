package com.mobile.moa.home

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.mobile.moa.asset.AssetResponse
import com.mobile.moa.databinding.FragmentHomeBinding
import com.mobile.moa.mileage.*
import com.mobile.moa.model.Shop
import com.mobile.moa.my.LoginActivity

/* written by keh
date: 22.07.04 */

class HomeFragment : Fragment(), ShopView, AssetView {

    lateinit var binding: FragmentHomeBinding
//    private var shopDatas = ArrayList<Shop>()

    private var shopService = ShopService()
    private var assetService = AssetService()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//       requireActivity().setStatusBarTransparent()

        binding = FragmentHomeBinding.inflate(inflater, container, false)

        //로그인 페이지 연결 살리기
        if(getJwt().equals(null)) {
            val intent = Intent(context, LoginActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
        }

        assetService.setAssetView(this)

//        getSeq()
//        getTotal()
//        if (getToken() != null) {
//            getTotal()
//        }

        shopService.setShopView(this)

//        binding.homeMileageRv.adapter = shopRVAdapter
        binding.homeMileageRv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.homeMileageRv.setHasFixedSize(true)

        getShopList()

        return binding.root
    }

    private fun getTotal() {
//        assetService.total(getToken()!!, getSeq()!!)
        assetService.total("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOiIxMTAxMDEwODk4Iiwic2NvcGUiOlsiaW5xdWlyeSIsImxvZ2luIiwidHJhbnNmZXIiXSwiaXNzIjoiaHR0cHM6Ly93d3cub3BlbmJhbmtpbmcub3Iua3IiLCJleHAiOjE2NzI4NTIwNjYsImp0aSI6Ijk2OGViNmExLTgwNzAtNDI1ZS05YmNjLTM2MTIxMTFkYjE5OCJ9.qCfFiZUtVFSb-zcYOpJUys77CbGr9d9VLz4PArThgpw","1101010898")
    }

    private fun getShopList() {
        if (getJwt() == null) {
            Toast.makeText(activity, "로그인이 필요한 서비스입니다.", Toast.LENGTH_SHORT).show()
        }
        else {
            shopService.getShopList(getJwt()!!)
        }
    }

    private fun getMemberId(): Long {
        val memberId = activity?.getSharedPreferences("auth", AppCompatActivity.MODE_PRIVATE)
        return memberId!!.getLong("memberId", 4)
    }

    private fun getJwt(): String? {
        val jwt = activity?.getSharedPreferences("auth", AppCompatActivity.MODE_PRIVATE)
        return jwt!!.getString("jwt", null)
    }
//
//    override fun onShopListSuccess(shopList: List<ShopResponse>) {
//        val shopAdapter = ShopListRAdapter(shopList)
//        binding.shopListRv.adapter = shopAdapter.


    override fun onShopListSuccess(shopList: List<ShopResponse>) {
        val shopRVAdapter = ShopRVAdapter(shopList)
        binding.homeMileageRv.adapter = shopRVAdapter
    }

    override fun onShopListFailure() {
        Log.d("shopList-fragment", "fail")
    }

    override fun onAddScrapSuccess() {
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

    private fun getToken(): String? {
        val token = activity?.getSharedPreferences("access_token", AppCompatActivity.MODE_PRIVATE)
        Log.d("token-now", token?.getString("access_token", null).toString())
        return token?.getString("access_token", null)
    }

    private fun getSeq(): String? {
        val token = activity?.getSharedPreferences("access_token", AppCompatActivity.MODE_PRIVATE)
        Log.d("seq-now", token?.getString("seq", null).toString())
        return token?.getString("seq", null)
    }

    override fun onGetTotalSuccess(assetResponse: AssetResponse) {
        binding.homeAssetTv.text = assetResponse.available_amt
    }


}