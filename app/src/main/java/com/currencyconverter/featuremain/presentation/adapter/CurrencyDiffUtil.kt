package com.currencyconverter.featuremain.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.currencyconverter.featuremain.presentation.CurrencyUi

class CurrencyDiffUtil : DiffUtil.ItemCallback<CurrencyUi>() {

    override fun areItemsTheSame(oldItem: CurrencyUi, newItem: CurrencyUi): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: CurrencyUi, newItem: CurrencyUi): Boolean = oldItem == newItem
}
