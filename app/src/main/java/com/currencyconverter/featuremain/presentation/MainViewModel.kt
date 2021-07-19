package com.currencyconverter.featuremain.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.currencyconverter.domain.interactor.GetTodayCurrenciesInteractor
import com.currencyconverter.domain.model.Currency
import kotlinx.coroutines.launch

class MainViewModel(private val getTodayCurrenciesInteractor: GetTodayCurrenciesInteractor) : ViewModel() {

    // public live data region
    val currencies: LiveData<List<Currency>> get() = _currencies

    // private live data region
    private val _currencies = MutableLiveData<List<Currency>>()


    fun showCurrencies() {
        viewModelScope.launch {
            val todayCurrenciesResult = getTodayCurrenciesInteractor.invoke()
            todayCurrenciesResult.getOrNull()?.let {
                _currencies.postValue(it.currencies)
            } ?: run {
                // TODO: show error or smth
            }
        }
    }
}
