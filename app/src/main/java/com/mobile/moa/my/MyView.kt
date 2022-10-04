package com.mobile.moa.my

import com.mobile.moa.mileage.ShopResponse


/* written by keh
date: 22.07.31
edit: 22.08.02 */

interface MyView {
    fun onGetMySuccess(myResponse: MyResponse)
    fun onPutSchoolSuccess(myResponse: MyResponse)
    fun onUpdateMySuccess(myResponse: MyResponse)
//    fun onGetScrapListSuccess(scrapList: List<ShopResponse>)
}