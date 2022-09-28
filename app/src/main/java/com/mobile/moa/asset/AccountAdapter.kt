package com.mobile.moa.asset

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mobile.moa.R
import com.mobile.moa.databinding.FragmentAssetListBinding
import com.mobile.moa.databinding.ItemAssetBankbookBinding
import com.mobile.moa.model.Balance
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class AccountAdapter(private val myDataset: List<Balance>):
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    class MyViewHolder(var binding: ItemAssetBankbookBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_asset_bankbook, viewGroup, false)
        return MyViewHolder(ItemAssetBankbookBinding.bind(view))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding = (holder as MyViewHolder).binding
        binding.assetBankbookTv.text = myDataset[position].bank_name + " " + myDataset[position].product_name

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


