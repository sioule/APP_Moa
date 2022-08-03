package com.mobile.moa.my

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mobile.moa.R
import com.mobile.moa.databinding.FragmentMyBinding
import com.mobile.moa.databinding.FragmentSchoolBinding

/* written by keh
date: 22.08.02 */

class SchoolFragment : Fragment() {

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        arguments?.let {
//        }
//    }

    lateinit var binding: FragmentSchoolBinding
    lateinit var myService: MyService

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSchoolBinding.inflate(inflater, container, false)

        binding.schoolSearchBtn.setOnClickListener {
            searchSchool(binding.schoolEt.text.toString())
        }

        return binding.root
    }

    private fun searchSchool(school: String) {

    }

}