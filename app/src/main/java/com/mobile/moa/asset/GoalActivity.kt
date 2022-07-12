package com.mobile.moa.asset

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobile.moa.R
import com.mobile.moa.databinding.ActivityGoalBinding

class GoalActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGoalBinding
    private val data = arrayListOf<Goal>()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goal)

        binding = ActivityGoalBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        for (i in 1..10) {
            data.add(Goal("군것질", 1000))
        }

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = GoalAdapter(data)

    }
}