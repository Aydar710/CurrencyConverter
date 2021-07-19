package com.currencyconverter.data.repository

import com.currencyconverter.data.model.CurrenciesResponse
import com.currencyconverter.data.service.CurrenciesService
import com.currencyconverter.data.utils.makeApiCall

class CurrencyRepositoryImpl(private val currenciesService: CurrenciesService) : CurrencyRepository {

    override suspend fun getTodayCurrencies(): Result<CurrenciesResponse> {
        return makeApiCall { currenciesService.getTodayCurrencyValues() }
    }
}