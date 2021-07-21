package com.currencyconverter.featuremain.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.currencyconverter.R
import com.currencyconverter.databinding.ActivityMainBinding
import com.currencyconverter.featuremain.presentation.adapter.CurrenciesAdapter
import com.currencyconverter.featuremain.presentation.adapter.VerticalSpaceItemDecoration
import com.currencyconverter.featuremain.utils.dateAndTimeShort
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModel()
    private var currenciesAdapter: CurrenciesAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initAdapter()
        initSwipeToRefresh()
        observeViewModel()
        viewModel.showCurrencies()
    }

    private fun observeViewModel() {
        with(viewModel) {
            currencies.observe(this@MainActivity) { currenciesAdapter?.submitList(it) }
            actualDataDate.observe(this@MainActivity, ::setupActualDate)
            shouldShowErrorText.observe(this@MainActivity, ::setErrorTextVisibility)
            loading.observe(this@MainActivity, ::setRefreshState)
        }
    }

    private fun setErrorTextVisibility(visible: Boolean) {
        binding.tvLoadError.isVisible = visible
    }

    private fun setupActualDate(date: Date) {
        binding.tvActualDate.text = getString(R.string.data_as_of, date.dateAndTimeShort())
    }

    private fun setRefreshState(isRefreshing: Boolean) {
        binding.swipeMain.isRefreshing = isRefreshing
    }

    private fun initAdapter() {
        currenciesAdapter = CurrenciesAdapter { newValue, changedPosition ->
            viewModel.onCurrencyValueChanged(newValue, changedPosition)
        }
        binding.rvCurrencies.adapter = currenciesAdapter
        binding.rvCurrencies.addItemDecoration(
            VerticalSpaceItemDecoration(resources.getDimensionPixelSize(R.dimen.recycler_view_item_space))
        )
    }

    private fun initSwipeToRefresh() {
        binding.swipeMain.setOnRefreshListener {
            viewModel.showCurrencies()
        }
    }
}
