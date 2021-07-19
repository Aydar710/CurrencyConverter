package com.currencyconverter.featuremain.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.currencyconverter.R
import com.currencyconverter.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModel()
    private var currenciesAdapter: CurrenciesAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initAdapter()
        observeViewModel()
        viewModel.showCurrencies()
    }

    private fun observeViewModel() {
        with(viewModel) {
            currencies.observe(this@MainActivity) {
                currenciesAdapter?.submitList(it)
            }
        }
    }

    private fun initAdapter() {
        currenciesAdapter = CurrenciesAdapter()
        binding.rvCurrencies.adapter = currenciesAdapter
        binding.rvCurrencies.addItemDecoration(
            VerticalSpaceItemDecoration(resources.getDimensionPixelSize(R.dimen.recycler_view_item_space))
        )
    }
}
