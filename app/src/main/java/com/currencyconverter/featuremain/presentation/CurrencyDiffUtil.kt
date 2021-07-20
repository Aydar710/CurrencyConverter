package com.currencyconverter.featuremain.presentation

import androidx.recyclerview.widget.DiffUtil

class CurrencyDiffUtil : DiffUtil.ItemCallback<CurrencyUi>() {

    override fun areItemsTheSame(oldItem: CurrencyUi, newItem: CurrencyUi): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: CurrencyUi, newItem: CurrencyUi): Boolean = oldItem == newItem
}
