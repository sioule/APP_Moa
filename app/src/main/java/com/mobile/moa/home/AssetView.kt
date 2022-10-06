package com.mobile.moa.home

import com.mobile.moa.asset.AssetResponse

interface AssetView {
    fun onGetTotalSuccess(assetResponse: AssetResponse)
}