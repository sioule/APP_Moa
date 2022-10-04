package com.mobile.moa.my

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mobile.moa.R
import com.mobile.moa.databinding.ItemAssetGoalBinding
import com.mobile.moa.model.Goal

class GoalAdapter(private val myDataset: List<GoalResponse>):
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    class MyViewHolder(var binding: ItemAssetGoalBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_asset_goal, viewGroup, false)
        return MyViewHolder(ItemAssetGoalBinding.bind(view))
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding = (holder as MyViewHolder).binding
        binding.goalCheckbox.isClickable = myDataset[position].completed
        binding.goalItem.setText(myDataset[position].content)
        binding.goalItemCashTv.setText(myDataset[position].price.toString())

        binding.itemRoot.setOnClickListener{
            itemClickListener.onClick(it, position)
        }
    }

    interface OnItemClickListener {
        fun onClick(v: View, position: Int)
    }

    fun setItemClickListener(onItemClickListener: OnItemClickListener) {
        this.itemClickListener = onItemClickListener
    }

    private lateinit var itemClickListener : OnItemClickListener

    override fun getItemCount(): Int = myDataset.size

}