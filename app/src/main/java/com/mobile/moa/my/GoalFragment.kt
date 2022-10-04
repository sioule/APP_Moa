package com.mobile.moa.my

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobile.moa.databinding.FragmentGoalBinding
import com.mobile.moa.model.Goal
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDate

lateinit var goalData: List<Goal>
lateinit var goal: Goal

var memberId = 1 // TODO memberId 불러오기

class GoalFragment : Fragment() {
    lateinit var binding: FragmentGoalBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGoalBinding.inflate(inflater, container, false)

        getGoal()

        return binding.root
    }

    // 목표 관리 조회
    fun getGoal() {
        var date = "2022-10" // TODO date 수정
        val call: Call<List<Goal>> = ServiceCreator.service.getGoal(date, memberId.toLong())
        call.enqueue(object : Callback<List<Goal>> {
            override fun onResponse(call: Call<List<Goal>>, response: Response<List<Goal>>) {
                if (response.isSuccessful) {
                    goalList = response.body()!!
                } else {
                    goalList = listOf()
                }
                setAdapter()
            }

            override fun onFailure(call: Call<List<Goal>>, t: Throwable) {
                //Toast.makeText(context, "서버 연결 실패", Toast.LENGTH_SHORT).show()
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

        binding.goalList.layoutManager = LinearLayoutManager(context)
        binding.goalList.adapter = goalAdapter
        goalAdapter.setItemClickListener(object: GoalAdapter.OnItemClickListener{
            override fun onClick(v: View, position: Int) {

            }
        })
    }

}