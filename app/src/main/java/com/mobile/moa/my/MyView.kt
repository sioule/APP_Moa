package com.mobile.moa.my

interface MyView {
    fun onGetMySuccess(myResponse: MyResponse)
    fun onPutSchoolSuccess(myResponse: MyResponse)
    fun onUpdateMySuccess(myResponse: MyResponse)
}