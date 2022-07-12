package com.mobile.moa.asset

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mobile.moa.R
import com.mobile.moa.databinding.ItemAssetGoalBinding

class GoalAdapter(private val myDataset: List<Goal>):
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    class MyViewHolder(var binding: ItemAssetGoalBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_asset_goal, viewGroup, false)
        return MyViewHolder(ItemAssetGoalBinding.bind(view))
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding = (holder as MyViewHolder).binding
        binding.itemData.text = myDataset[position].goal
    }

    override fun getItemCount(): Int = myDataset.size

}