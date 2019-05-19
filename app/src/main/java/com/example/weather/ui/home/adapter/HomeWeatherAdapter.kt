package com.example.weather.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.bases.ui.BaseViewData
import com.example.weather.bases.ui.BaseViewDataHolder
import com.example.weather.databinding.ItemWeatherSummaryBinding


class HomeWeatherAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val items: MutableList<BaseViewData> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder = when (viewType) {
        HomeWeatherItems.ITEM_SUMMARY -> SummaryViewHolder(
            ItemWeatherSummaryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
        else -> {
            throw IllegalArgumentException("Unsupported type in HomeWeatherAdapter. Please refer to HomeWeatherItems.")
        }
    }


    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is BaseViewDataHolder) {
            holder.bindViewData(items[position])
        }
    }

    override fun getItemViewType(position: Int) = items[position].itemViewType

    fun setData(data: List<BaseViewData>) {
        this.items.clear()
        this.items.addAll(data)
        notifyDataSetChanged()
    }
}