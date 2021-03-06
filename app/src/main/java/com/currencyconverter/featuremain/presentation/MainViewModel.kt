package com.currencyconverter.featuremain.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.currencyconverter.domain.interactor.CurrencyConverterInteractor
import com.currencyconverter.domain.interactor.GetRatesFromCacheInteractor
import com.currencyconverter.domain.interactor.GetTodayCurrenciesInteractor
import com.currencyconverter.domain.interactor.SaveRatesToDatabaseInteractor
import com.currencyconverter.domain.model.Currencies
import com.currencyconverter.domain.model.ExchangeRate
import com.currencyconverter.featuremain.utils.NetworkConnectionUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.math.BigDecimal
import java.util.*

class MainViewModel(
    private val getTodayCurrenciesInteractor: GetTodayCurrenciesInteractor,
    private val currencyConverterInteractor: CurrencyConverterInteractor,
    private val saveRatesToDatabaseInteractor: SaveRatesToDatabaseInteractor,
    private val getRatesFromCacheInteractor: GetRatesFromCacheInteractor,
    private val networkConnectionUtils: NetworkConnectionUtils
) : ViewModel() {
    companion object {
        private const val DEFAULT_VALUE_IN_RUBLES = 100.0
    }

    // public live data region
    val currencies: LiveData<List<CurrencyUi>> get() = _currencies
    val actualDataDate: LiveData<Date> get() = _actualDataDate
    val shouldShowErrorText: LiveData<Boolean> get() = _shouldShowErrorText
    val loading: LiveData<Boolean> get() = _loading

    // private live data region
    private val _currencies = MutableLiveData<List<CurrencyUi>>()
    private val _actualDataDate = MutableLiveData<Date>()
    private val _shouldShowErrorText = MutableLiveData<Boolean>()
    private val _loading = MutableLiveData<Boolean>()

    private lateinit var exchangeRates: MutableList<ExchangeRate>

    fun onCurrencyValueChanged(newValue: Double, changedPosition: Int) {
        val changedCurrencyUi = currencies.value?.get(changedPosition)
        val changedExchangeRate = exchangeRates.find { it.id == changedCurrencyUi?.id }
        changedExchangeRate?.id?.let { recalculateCurrencies(it, newValue) }
    }

    fun showCurrencies() {
        _shouldShowErrorText.postValue(false)
        viewModelScope.launch {
            if (networkConnectionUtils.isNetworkAvailable()) {
                showCurrenciesFromNetwork()
            } else {
                showCurrenciesFromCache()
            }
        }
    }

    private suspend fun showCurrenciesFromNetwork() {
        _loading.postValue(true)
        val todayCurrenciesResult = getTodayCurrenciesInteractor.invoke()
        todayCurrenciesResult.getOrNull()?.let {
            _actualDataDate.postValue(it.date)
            changeExchangeRates(it)
            _currencies.postValue(exchangeRates.mapToCurrencyUiWithDefaultValue(DEFAULT_VALUE_IN_RUBLES))
            _loading.postValue(false)
            saveRatesToDatabaseInteractor.invoke(it)
        } ?: run {
            _shouldShowErrorText.postValue(true)
            _loading.postValue(false)
        }
    }

    private suspend fun showCurrenciesFromCache() {
        _loading.postValue(true)
        val cacheCurrencies = getRatesFromCacheInteractor.invoke()
        cacheCurrencies?.let { cacheCurrenciesNotNull ->
            _actualDataDate.postValue(cacheCurrenciesNotNull.date)
            changeExchangeRates(cacheCurrenciesNotNull)
            _currencies.postValue(exchangeRates.mapToCurrencyUiWithDefaultValue(DEFAULT_VALUE_IN_RUBLES))
        } ?: run {
            _shouldShowErrorText.postValue(true)
        }
        _loading.postValue(false)
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

    private fun recalculateCurrencies(currencyId: String, newValue: Double) = viewModelScope.launch(Dispatchers.Default) {
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
