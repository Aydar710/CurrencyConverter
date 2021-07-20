package com.currencyconverter.data.repository

import com.currencyconverter.data.model.remote.ExchangeRatesResponse
import com.currencyconverter.data.service.ExchangeRatesService
import com.currencyconverter.data.utils.makeApiCall

class ExchangeRateRepositoryImpl(private val exchangeRatesService: ExchangeRatesService) : ExchangeRateRepository {

    override suspend fun getRates(): Result<ExchangeRatesResponse> {
        return makeApiCall { exchangeRatesService.getTodayCurrencyValues() }
    }
}