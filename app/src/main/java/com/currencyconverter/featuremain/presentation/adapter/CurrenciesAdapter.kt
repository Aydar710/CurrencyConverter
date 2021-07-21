package com.currencyconverter.featuremain.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.currencyconverter.databinding.ItemCurrencyBinding
import com.currencyconverter.featuremain.presentation.CurrencyUi
import java.util.*

class CurrenciesAdapter(
    private val valueChanged: (Double, Int) -> Unit
) : ListAdapter<CurrencyUi, CurrenciesAdapter.CurrencyViewHolder>(CurrencyDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCurrencyBinding.inflate(inflater, parent, false)
        return CurrencyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class CurrencyViewHolder(private val binding: ItemCurrencyBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.etValue.doAfterTextChanged {
                if (binding.etValue.isFocused) {
                    if (it.toString().isNotEmpty()) {
                        valueChanged.invoke(it.toString().toDouble(), adapterPosition)
                    } else {
                        valueChanged.invoke(0.0, adapterPosition)
                    }
                }
            }
        }

        fun bind(currency: CurrencyUi) {
            with(binding) {
                tvCurrencyCode.text = currency.charCode
                tvCurrecnyName.text = currency.name
                etValue.setText("%.2f".format(Locale.ROOT, currency.value))
            }
        }
    }
}
