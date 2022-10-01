package com.mobile.moa.my

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobile.moa.R
import com.mobile.moa.databinding.ActivitySchoolBinding

class SchoolActivity : AppCompatActivity(), SchoolView {

    lateinit var binding: ActivitySchoolBinding
    private var myService = MyService()
    lateinit var school: RequestSchool

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_school)

        binding = ActivitySchoolBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 어댑터
        binding.schoolListRv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.schoolListRv.setHasFixedSize(true)

        //서비스 - 뷰 연결
        myService.setSchoolView(this)

        //검색 버튼 - 지도 api
        binding.schoolSearchBtn.setOnClickListener {
            searchSchool(binding.schoolEt.text.toString())
        }

        binding.schoolPutBtn.setOnClickListener {
            Log.d("put-school-btn", school.userSchool)
            myService.putSchool(getJwt(), school)
            finish()
        }
    }

        //지도에서 검색 후 학교 위치, 이름 가져오기
        private fun searchSchool(school: String) {
            myService.searchSchool(school)
        }

        override fun onSearchSchoolSuccess(schoolResponse: SchoolResponse) {
            Log.d("school-fragment-success", schoolResponse.candidates[0].name)
            if (schoolResponse.status == "OK") {
                val schoolRAdapter = SchoolRAdapter(schoolResponse.candidates)
                binding.schoolListRv.adapter = schoolRAdapter.apply {
                    this.setMyItemClickListener(object : SchoolRAdapter.MyItemClickListener{
                        override fun onClick(position: Int) {
                            school = RequestSchool(
                                schoolResponse.candidates[position].name,
                                schoolResponse.candidates[position].geometry.location.lat.toString(),
                                schoolResponse.candidates[position].geometry.location.lng.toString()
                            )
                        }
                    })
                }
            }
            else {
                Toast.makeText(this,"결과가 존재하지 않습니다.", Toast.LENGTH_LONG).show()
            }
        }

        override fun onSearchSchoolFail() {
            Log.d("school-fragment-success", "fail")
        }

    private fun getJwt(): Long {
        val memberId = this.getSharedPreferences("auth", AppCompatActivity.MODE_PRIVATE)
        return memberId!!.getLong("jwt", 0)
    }
}