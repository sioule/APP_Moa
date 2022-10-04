package com.mobile.moa.mileage

class ShopService {
    private lateinit var shopView: ShopView

    fun setShopView(shopView: ShopView) {
        this.shopView = shopView
    }

    fun getShopList(memberId: Long, jwt: String) {

    }
}