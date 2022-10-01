package com.mobile.moa.my

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.mobile.moa.R
import com.mobile.moa.databinding.ActivitySchoolBinding
import com.mobile.moa.databinding.FragmentSchoolBinding

class SchoolActivity : AppCompatActivity(), SchoolView {

    lateinit var binding: ActivitySchoolBinding
    private var myService = MyService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_school)

        binding = ActivitySchoolBinding.inflate(layoutInflater)
        setContentView(binding.root)

        myService.setSchoolView(this)

        binding.schoolSearchBtn.setOnClickListener {
            searchSchool(binding.schoolEt.text.toString())
        }
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
            Log.d("school-fragment-success", "fail")
        }
}