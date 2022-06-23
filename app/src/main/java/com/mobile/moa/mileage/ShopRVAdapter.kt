package com.mobile.moa.mileage

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mobile.moa.databinding.ItemHomeShopBinding

/*written by keh
date: 22.06.24*/

class ShopRVAdapter(private val shopList: ArrayList<Shop>) : RecyclerView.Adapter<ShopRVAdapter.ViewHolder>() {
    class ViewHolder(val binding: ItemHomeShopBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(shop: Shop) {
            binding.itemAlbumCoverImgIv.setImageResource(shop.img!!)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}