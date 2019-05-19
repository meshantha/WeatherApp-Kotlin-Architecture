package com.example.weather.ui.home.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.weather.bases.ui.BaseViewData
import com.example.weather.bases.ui.BaseViewDataHolder
import com.example.weather.databinding.ItemWeatherSummaryBinding

data class SummaryViewData(
    val currentTemperature: String,
    val minMaxTemperature: String,
    val weatherStatus: String,
    override val rawData: Any,
    override val itemViewType: Int
) : BaseViewData

class SummaryViewHolder(private val binding: ItemWeatherSummaryBinding) : RecyclerView.ViewHolder(binding.root),
    BaseViewDataHolder {
    override fun bindViewData(viewData: BaseViewData) {
        if (viewData is SummaryViewData) {
            binding.viewData = viewData
        } else {
            throw IllegalArgumentException("SummaryViewHolder has to bind with SummaryViewData")
        }
    }
}