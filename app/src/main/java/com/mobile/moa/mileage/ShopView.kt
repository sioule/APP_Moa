package com.mobile.moa.mileage

interface ShopView {
    fun onShopListSuccess(shopList: List<ShopResponse>)
    fun onShopListFailure()

    fun onAddScrapSuccess()
}