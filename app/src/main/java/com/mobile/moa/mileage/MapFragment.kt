package com.mobile.moa.mileage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mobile.moa.databinding.FragmentAssetBinding
import com.mobile.moa.databinding.FragmentMapBinding

/* written by keh
date: 22.05.30 */

class MapFragment : Fragment() {

    lateinit var binding: FragmentMapBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMapBinding.inflate(inflater, container, false)

        return binding.root
    }

}