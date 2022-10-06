package com.mobile.moa.mileage

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mobile.moa.databinding.ItemHomeShopBinding
import com.mobile.moa.model.Shop

/*written by keh
date: 22.06.24*/

class ShopRVAdapter(private val shopList: List<ShopResponse>) : RecyclerView.Adapter<ShopRVAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemHomeShopBinding = ItemHomeShopBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(shopList[position])
    }

    override fun getItemCount(): Int = shopList.size

    inner class ViewHolder(val binding: ItemHomeShopBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(shop: ShopResponse) {
            Log.d("shop-adapter", "bind")
//            binding.itemAlbumCoverImgIv.setImageResource()
            binding.shopNameItemTv.text = shop.name
        }
    }

//    class ShopListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        val name: TextView = itemView.findViewById(R.id.shop_name_item_tv)
////        val address : TextView = itemView.findViewById(R.id.school_address_item_tv)
//    }

}