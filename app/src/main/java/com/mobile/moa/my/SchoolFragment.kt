package com.mobile.moa.my

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mobile.moa.R
import com.mobile.moa.databinding.FragmentMyBinding
import com.mobile.moa.databinding.FragmentSchoolBinding
import kotlin.math.log

class SchoolFragment : Fragment(), SchoolView {


    lateinit var binding: FragmentSchoolBinding
    lateinit var myService: MyService

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSchoolBinding.inflate(inflater, container, false)
        myService.setSchoolView(this)

        binding.schoolSearchBtn.setOnClickListener {
            searchSchool(binding.schoolEt.text.toString())
        }

        return binding.root
    }

    //지도에서 검색 후 학교 위치, 이름 가져오기
    private fun searchSchool(school: String) {
        myService.searchSchool(school)
//        myService.putSchool(school)
    }

    override fun onSearchSchoolSuccess(schoolResponse: SchoolResponse) {
        Log.d("school-fragment-success", schoolResponse.candidates[0].name)
    }

    override fun onSearchSchoolFail() {
        TODO("Not yet implemented")
    }

    override fun onPutSchoolSuccess(myResponse: MyResponse) {
        TODO("Not yet implemented")
    }

}