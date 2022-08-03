package com.mobile.moa.mileage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayoutMediator
import com.mobile.moa.databinding.FragmentMileageShopBinding

/* written by keh
date: 22.06.05 */

class MileageShopFragment : Fragment() {

    lateinit var binding: FragmentMileageShopBinding

    private val shop = arrayListOf("지도", "목록")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMileageShopBinding.inflate(inflater, container, false)

        val shopAdapter = MileageShopVPAdapter(this)
        binding.mileageShopContentVp.adapter = shopAdapter
        TabLayoutMediator(binding.mileageShopTb, binding.mileageShopContentVp) {
            tab, position ->
            tab.text = shop[position]
        }.attach()

        return binding.root
    }
}