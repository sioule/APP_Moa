package com.mobile.moa.my

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobile.moa.databinding.FragmentShopListBinding
import com.mobile.moa.mileage.ShopListRAdapter
import com.mobile.moa.mileage.ShopResponse

class ScrapListFragment : Fragment(), ScrapView {

    lateinit var binding: FragmentShopListBinding
    private var myService = MyService()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentShopListBinding.inflate(inflater, container, false)
        myService.setScrapView(this)

        // 어댑터
        binding.shopListRv.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        binding.shopListRv.setHasFixedSize(true)

        getShopList()

        return binding.root
    }

    private fun getShopList() {
        if (getJwt() == null) {
            Toast.makeText(activity, "로그인이 필요한 서비스입니다.", Toast.LENGTH_SHORT).show()
        }
        else {
            myService.getMyScrapList(getMemberId(), getJwt()!!)
        }
    }

    override fun onGetScrapListSuccess(scrapList: List<ShopResponse>) {
        val shopAdapter = ShopListRAdapter(scrapList)
        binding.shopListRv.adapter = shopAdapter.apply {
            this.setMyItemClickListener(object : ShopListRAdapter.MyItemClickListener {
                override fun onClick(position: Int) {

                }
            })
        }
    }


    override fun onGetScrapListFailure() {
        Log.d("shop-fragment", "fail")
    }


    private fun getMemberId(): Long {
        val memberId = activity?.getSharedPreferences("auth", AppCompatActivity.MODE_PRIVATE)
        return memberId!!.getLong("memberId", 0)
    }

    private fun getJwt(): String? {
        val jwt = activity?.getSharedPreferences("auth", AppCompatActivity.MODE_PRIVATE)
        return jwt!!.getString("jwt", null)
    }

}