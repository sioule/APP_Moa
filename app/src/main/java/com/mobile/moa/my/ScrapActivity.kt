package com.mobile.moa.my

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobile.moa.R
import com.mobile.moa.databinding.ActivityScrapBinding
import com.mobile.moa.databinding.FragmentShopListBinding
import com.mobile.moa.mileage.ShopListRAdapter
import com.mobile.moa.mileage.ShopResponse

class ScrapActivity : AppCompatActivity(), ScrapView {

    lateinit var binding: ActivityScrapBinding
    private var myService = MyService()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scrap)
        binding = ActivityScrapBinding.inflate(layoutInflater)
        myService.setScrapView(this)

        // 어댑터
        binding.scrapListRv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.scrapListRv.setHasFixedSize(true)

        getShopList()

    }

    private fun getShopList() {
        if (getJwt() == null) {
            Toast.makeText(this, "로그인이 필요한 서비스입니다.", Toast.LENGTH_SHORT).show()
        }
        else {
            myService.getMyScrapList(getMemberId(), getJwt()!!)
        }
    }

    override fun onGetScrapListSuccess(scrapList: List<ShopResponse>) {
        val shopAdapter = ShopListRAdapter(scrapList)
        binding.scrapListRv.adapter = shopAdapter.apply {
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
        val memberId = this.getSharedPreferences("auth", AppCompatActivity.MODE_PRIVATE)
        return memberId!!.getLong("memberId", 0)
    }

    private fun getJwt(): String? {
        val jwt = this.getSharedPreferences("auth", AppCompatActivity.MODE_PRIVATE)
        return jwt!!.getString("jwt", null)
    }

}