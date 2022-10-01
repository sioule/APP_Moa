package com.mobile.moa.my

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mobile.moa.R
import com.mobile.moa.asset.AccountAdapter
import kotlinx.coroutines.NonDisposableHandle.parent

class SchoolRAdapter(private val schoolList: List<Candidates>): RecyclerView.Adapter<SchoolRAdapter.SchoolViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SchoolViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_school_item, parent, false)

        return SchoolViewHolder(view).apply {
            itemView.setOnClickListener {
                putSchool(adapterPosition)
            }
        }
    }

    private fun putSchool(position: Int) {
        Log.d("학교 선택", schoolList[position].name)
        itemClickListener.onClick(position)
    }

    interface MyItemClickListener {
        fun onClick(position: Int)
    }

    fun setMyItemClickListener(myItemClickListener: MyItemClickListener){
        this.itemClickListener = myItemClickListener
        Log.d("학교 선택", "my-adapter")
    }

    private lateinit var itemClickListener : MyItemClickListener

    override fun onBindViewHolder(holder: SchoolViewHolder, position: Int) {
        holder.name.text = schoolList[position].name
        holder.address.text = schoolList[position].formatted_address
    }

    override fun getItemCount(): Int {
        return schoolList.size
    }

    class SchoolViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.school_name_item_tv)
        val address : TextView = itemView.findViewById(R.id.school_address_item_tv)
    }

}