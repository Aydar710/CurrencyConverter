package com.currencyconverter.data.repository

import com.currencyconverter.data.model.CurrenciesResponse
import com.currencyconverter.data.service.CurrenciesService
import com.currencyconverter.data.utils.makeApiCall

class CurrencyRepository(private val currenciesService: CurrenciesService) {

    suspend fun getTodayCurrencies(): Result<CurrenciesResponse> {
        return makeApiCall { currenciesService.getTodayCurrencyValues() }
    }
}