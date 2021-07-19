package com.currencyconverter.featuremain.presentation

import androidx.recyclerview.widget.DiffUtil
import com.currencyconverter.domain.model.Currency

class CurrencyDiffUtil : DiffUtil.ItemCallback<Currency>() {

    override fun areItemsTheSame(oldItem: Currency, newItem: Currency): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Currency, newItem: Currency): Boolean = oldItem == newItem
}
