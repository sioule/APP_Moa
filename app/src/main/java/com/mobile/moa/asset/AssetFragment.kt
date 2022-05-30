package com.mobile.moa.asset

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mobile.moa.databinding.FragmentAssetBinding

class AssetFragment : Fragment() {

    lateinit var binding: FragmentAssetBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAssetBinding.inflate(inflater, container, false)

        return binding.root
    }
    
}