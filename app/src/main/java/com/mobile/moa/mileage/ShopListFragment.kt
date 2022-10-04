package com.mobile.moa.mileage

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobile.moa.databinding.FragmentShopListBinding

class ShopListFragment : Fragment(), ShopView {

    lateinit var binding: FragmentShopListBinding
    private var shopService = ShopService()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentShopListBinding.inflate(inflater, container, false)
        shopService.setShopView(this)

        // 어댑터
        binding.shopListRv.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        binding.shopListRv.setHasFixedSize(true)


        return binding.root
    }


    override fun onShopListSuccess(shopList: List<ShopResponse>) {
        val shopAdapter = ShopListRAdapter(shopList)
        binding.shopListRv.adapter = shopAdapter.apply {
            this.setMyItemClickListener(object : ShopListRAdapter.MyItemClickListener{
                override fun onClick(position: Int) {
//                    binding.putSchoolEt.setText(school.userSchool)
                }
            })
        }
    }

    override fun onShopListFailure() {
        Log.d("shop-fragment", "fail")
    }
}