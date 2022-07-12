package com.mobile.moa.asset

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mobile.moa.databinding.FragmentAssetBinding

/* written by keh
date: 22.05.30 */

class AssetFragment : Fragment() {

    lateinit var binding: FragmentAssetBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAssetBinding.inflate(inflater, container, false)

        binding.tvManage.setOnClickListener {
            activity?.let {
                val intent = Intent(context, GoalActivity::class.java)
                startActivity(intent)
            }
        }
            return binding.root
        }
    }
    
