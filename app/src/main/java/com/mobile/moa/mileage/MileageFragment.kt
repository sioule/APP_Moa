package com.mobile.moa.mileage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mobile.moa.databinding.FragmentMileageBinding

/* written by keh
date: 22.05.30 */

class MileageFragment : Fragment() {

    lateinit var binding: FragmentMileageBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMileageBinding.inflate(inflater, container, false)

        return binding.root
    }
}