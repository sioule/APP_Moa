package com.mobile.moa.my

import com.mobile.moa.model.Goal
import retrofit2.http.*

interface GoalRetrofitInterface {
    // 목표 관리 작성
    @POST("/goal/{memberId}")
    fun createGoal(
        @Path("memberId") memberId: Long,
        @Body goalRequest: GoalRequest
    ) : retrofit2.Call<Goal>

    // 목표 관리 수정
    @PUT("/goal/update/{goalId}")
    fun updateGoal(
        @Path("goalId") goalId: Long,
        @Body goalRequest: GoalRequest
    ) : retrofit2.Call<Goal>

    // 목표 관리 조회
    @GET("/goal/{memberId}")
    fun getGoal(
        @Query("date") date: String,
        @Path("memberId") memberId: Long,
    ) : retrofit2.Call<List<Goal>>

    // 목표 달성 삭제
    @DELETE("/goal/{goalId}")
    fun deleteGoal(@Path("goalId") goalId: Long) : retrofit2.Call<Long>

    // 월별 달성률 조회
    @GET("/goal/rate/{memberId}")
    fun getAchievementRate(
        @Query("date") date: String,
        @Path("memberId") memberId: Long,
    ) : retrofit2.Call<Int>
}