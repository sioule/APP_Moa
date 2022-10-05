package com.mobile.moa.model

data class Goal (
    val goalId: Long,
    val content: String,
    val price: Long,
    val completed: Boolean,
    val date: String,
)
