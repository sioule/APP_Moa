package com.mobile.moa.my

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobile.moa.databinding.ActivityGoalBinding
import com.mobile.moa.model.Goal
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GoalActivity : AppCompatActivity() {
    lateinit var binding: ActivityGoalBinding
    lateinit var goalData: List<Goal>
    lateinit var goal: Goal
    var memberId = 1 // TODO memberId 불러오기

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getGoal()
        getAchievementRate()

        binding = ActivityGoalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 목표 관리 추가
        //binding.goalAdd.setOnClickListener()

        // 목표 관리 수정 및 삭제
    }


    // 목표 관리 조회
    fun getGoal() {
        var date = "2022-10" // TODO date 수정
        val call: Call<List<Goal>> = ServiceCreator.service.getGoal(date, memberId.toLong())
        call.enqueue(object : Callback<List<Goal>> {
            override fun onResponse(call: Call<List<Goal>>, response: Response<List<Goal>>) {
                if (response.isSuccessful) {
                    goalData = response.body()!!
                } else {
                    val list1= Goal(1.toLong(), "군것질거리",10000, false, "2020-10")
                    val list2= Goal(2.toLong(), "교통비",20000, false, "2022-10")
                    val list3= Goal(3.toLong(), "콘서트",100000, false, "2022-10")
                    val list4= Goal(4.toLong(), "매점",8000, false, "2022-10")
                    val list5= Goal(5.toLong(), "편의점",9000, false, "2022-10")
                    val list6= Goal(6.toLong(), "떡볶이",5000, false, "2022-10")

                    val testList = ArrayList<Goal>()
                    testList.add(list1)
                    testList.add(list2)
                    testList.add(list3)
                    testList.add(list4)
                    testList.add(list5)
                    testList.add(list6)

                    goalData = testList
                }
                setAdapter()
            }

            override fun onFailure(call: Call<List<Goal>>, t: Throwable) {
                //Toast.makeText(context, "서버 연결 실패", Toast.LENGTH_SHORT).show()
                val list1= Goal(1.toLong(), "군것질거리",10000, false, "2020-10")
                val list2= Goal(2.toLong(), "교통비",20000, false, "2022-10")
                val list3= Goal(3.toLong(), "콘서트",100000, false, "2022-10")
                val list4= Goal(4.toLong(), "매점",8000, false, "2022-10")
                val list5= Goal(5.toLong(), "편의점",9000, false, "2022-10")
                val list6= Goal(6.toLong(), "떡볶이",5000, false, "2022-10")

                val testList = ArrayList<Goal>()
                testList.add(list1)
                testList.add(list2)
                testList.add(list3)
                testList.add(list4)
                testList.add(list5)
                testList.add(list6)

                goalData = testList
            }

        })
    }

    // 목표달성률
    fun getAchievementRate() {
        var date = "2022-10" // TODO date 수정
        val call: Call<Int> = ServiceCreator.service.getAchievementRate(date, memberId.toLong())
        call.enqueue(object : Callback<Int> {
            override fun onResponse(call: Call<Int>, response: Response<Int>) {
                if (response.isSuccessful) {
                    var rate = response.body()!!
                } else {
                    //
                }
            }

            override fun onFailure(call: Call<Int>, t: Throwable) {
                //Toast.makeText(context, "서버 연결 실패", Toast.LENGTH_SHORT).show()
            }

        })
    }

    // 목표 관리 생성
    fun createGoal() {
        var goalRequest = GoalRequest(
            content = "",
            price= 1000,
            completed= false,
            date = "2022-10",
        )
        val call: Call<Goal> = ServiceCreator.service.createGoal(memberId.toLong(), goalRequest)
        call.enqueue(object : Callback<Goal> {
            override fun onResponse(call: Call<Goal>, response: Response<Goal>) {
                if (response.isSuccessful) {
                    goal = response.body()!!
                } else {
                    // Toast.makeText(context, "실패", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Goal>, t: Throwable) {
                //Toast.makeText(context, "서버 연결 실패", Toast.LENGTH_SHORT).show()
            }

        })
    }

    fun updateGoal() {
        var goalRequest = GoalRequest(
            content = "",
            price= 1000,
            completed= false,
            date = "2022-10",
        )
        val call: Call<Goal> = ServiceCreator.service.updateGoal(memberId.toLong(), goalRequest)
        call.enqueue(object : Callback<Goal> {
            override fun onResponse(call: Call<Goal>, response: Response<Goal>) {
                if (response.isSuccessful) {
                    goal = response.body()!!
                } else {
                    //
                }
            }

            override fun onFailure(call: Call<Goal>, t: Throwable) {
                //Toast.makeText(context, "서버 연결 실패", Toast.LENGTH_SHORT).show()
            }

        })
    }

    fun deleteGoal(goalId: Long) {
        val call: Call<Long> = ServiceCreator.service.deleteGoal(goalId)
        call.enqueue(object : Callback<Long> {
            override fun onResponse(call: Call<Long>, response: Response<Long>) {
                if (response.isSuccessful) {
                    //
                } else {
                    //
                }
            }

            override fun onFailure(call: Call<Long>, t: Throwable) {
                //Toast.makeText(context, "서버 연결 실패", Toast.LENGTH_SHORT).show()
            }

        })
    }

    private fun setAdapter(){
        val goalAdapter = GoalAdapter(goalData)

        binding.goalList.layoutManager = LinearLayoutManager(this)
        binding.goalList.adapter = goalAdapter
        goalAdapter.setItemClickListener(object: GoalAdapter.OnItemClickListener{
            override fun onClick(v: View, position: Int) {

            }
        })
    }
}