package com.mobile.moa.my

data class GoalRequest(
    val content: String,
    val price: Long,
    val completed: Boolean,
    val date: String,
)
