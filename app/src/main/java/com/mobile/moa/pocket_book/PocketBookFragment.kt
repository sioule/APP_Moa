package com.mobile.moa.pocket_book

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mobile.moa.databinding.FragmentAssetListBinding
import com.mobile.moa.databinding.FragmentPocketBookBinding

/* written by keh
date: 22.05.30 */

class PocketBookFragment : Fragment() {

    lateinit var binding: FragmentPocketBookBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPocketBookBinding.inflate(inflater, container, false)



        return binding.root
    }

}