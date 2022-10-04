package com.mobile.moa.mileage

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mobile.moa.R
import com.mobile.moa.my.SchoolResponse

class ShopListRAdapter(private val shopList: List<ShopResponse>): RecyclerView.Adapter<ShopListRAdapter.ShopListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_shop_item, parent, false)

        return ShopListViewHolder(view).apply {
            itemView.setOnClickListener {
//                putSchool(adapterPosition)
            }
        }
    }

//    private fun putSchool(position: Int) {
//        Log.d("학교 선택", shopList[position].name)
//        itemClickListener.onClick(position)
//    }

    interface MyItemClickListener {
        fun onClick(position: Int)
    }

    fun setMyItemClickListener(myItemClickListener: MyItemClickListener){
        this.itemClickListener = myItemClickListener
        Log.d("사용처 선택", "my-adapter")
    }

    private lateinit var itemClickListener : MyItemClickListener

    override fun onBindViewHolder(holder: ShopListViewHolder, position: Int) {
        holder.name.text = shopList[position].name
    }

    override fun getItemCount(): Int {
        return shopList.size
    }

    class ShopListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.shop_name_item_tv)
//        val address : TextView = itemView.findViewById(R.id.school_address_item_tv)
    }

}