package com.currencyconverter.featuremain.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.currencyconverter.databinding.ItemCurrencyBinding
import com.currencyconverter.domain.model.Currency

class CurrenciesAdapter : ListAdapter<Currency, CurrenciesAdapter.CurrencyViewHolder>(CurrencyDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCurrencyBinding.inflate(inflater, parent, false)
        return CurrencyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class CurrencyViewHolder(private val binding: ItemCurrencyBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(currency: Currency) {
            with(binding) {
                tvCurrencyCode.text = currency.charCode
                tvCurrecnyName.text = currency.name
                etValue.setText(currency.value.toString())
            }
        }
    }
}
