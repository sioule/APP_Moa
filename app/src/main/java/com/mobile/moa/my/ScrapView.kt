package com.mobile.moa.my

import com.mobile.moa.mileage.ShopResponse


/* written by keh
date: 22.07.31
edit: 22.08.02 */

interface ScrapView {
    fun onGetScrapListSuccess(scrapList: List<ShopResponse>)
    fun onGetScrapListFailure()
}