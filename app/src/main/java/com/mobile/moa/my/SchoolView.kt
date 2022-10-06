package com.mobile.moa.my

interface SchoolView {
    fun onSearchSchoolSuccess(schoolResponse: SchoolResponse)
    fun onSearchSchoolFail()

    fun onPutSchoolSuccess(myResponse: MyResponse)
}