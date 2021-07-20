package com.currencyconverter.featuremain.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.currencyconverter.domain.interactor.CurrencyConverterInteractor
import com.currencyconverter.domain.interactor.GetTodayCurrenciesInteractor
import com.currencyconverter.domain.model.Currencies
import com.currencyconverter.domain.model.ExchangeRate
import kotlinx.coroutines.launch
import java.math.BigDecimal

class MainViewModel(
    private val getTodayCurrenciesInteractor: GetTodayCurrenciesInteractor,
    private val currencyConverterInteractor: CurrencyConverterInteractor
) : ViewModel() {
    companion object {
        private const val DEFAULT_VALUE_IN_RUBLES = 100.0
    }

    // public live data region
    val currencies: LiveData<List<CurrencyUi>> get() = _currencies

    // private live data region
    private val _currencies = MutableLiveData<List<CurrencyUi>>()

    private lateinit var exchangeRates: MutableList<ExchangeRate>

    fun onCurrencyValueChanged(newValue: Double, changedPosition: Int) {
        val changedCurrencyUi = currencies.value?.get(changedPosition)
        val changedExchangeRate = exchangeRates.find { it.id == changedCurrencyUi?.id }
        changedExchangeRate?.id?.let { recalculateCurrencies(it, newValue) }
    }

    fun showCurrencies() {
        viewModelScope.launch {
            val todayCurrenciesResult = getTodayCurrenciesInteractor.invoke()
            todayCurrenciesResult.getOrNull()?.let {
                changeExchangeRates(it)
                _currencies.postValue(exchangeRates.mapToCurrencyUiWithDefaultValue(DEFAULT_VALUE_IN_RUBLES))
            } ?: run {
                // TODO: show error or smth
            }
        }
    }

    private fun changeExchangeRates(it: Currencies) {
        exchangeRates = mutableListOf<ExchangeRate>().apply {
            addAll(it.exchangeRates)
        }
    }

    private fun List<ExchangeRate>.mapToCurrencyUiWithDefaultValue(defaultValueInRubles: Double): List<CurrencyUi> {
        return map { currency ->
            val convertedDefaultValue = currencyConverterInteractor.invoke(
                BigDecimal(defaultValueInRubles),
                1.0,
                (currency.nominal.toDouble() / currency.value),
            )
            CurrencyUi(
                id = currency.id,
                numCode = currency.numCode,
                charCode = currency.charCode,
                name = currency.name,
                value = convertedDefaultValue.toDouble()
            )
        }
    }

    private fun recalculateCurrencies(currencyId: String, newValue: Double) {
        exchangeRates.find { it.id == currencyId }?.let { exchangeRateToChange ->
            val changedCurrencyRate = (exchangeRateToChange.nominal.toDouble() / exchangeRateToChange.value)
            val convertedList = mutableListOf<CurrencyUi>()
            currencies.value?.forEach { currentCurrencyUi ->
                exchangeRates.find { it.id == currentCurrencyUi.id }?.let { currentCurrencyRate ->
                    val convertedValue = currencyConverterInteractor.invoke(
                        BigDecimal(newValue),
                        changedCurrencyRate,
                        currentCurrencyRate.nominal.toDouble() / currentCurrencyRate.value
                    )
                    if (currentCurrencyUi.id != currencyId) {
                        convertedList.add(currentCurrencyUi.copy(value = convertedValue.toDouble()))
                    } else {
                        convertedList.add(currentCurrencyUi.copy())
                    }
                }
            }
            _currencies.postValue(convertedList)
        }
    }
}
