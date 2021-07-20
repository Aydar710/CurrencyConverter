package com.currencyconverter.data.repository

import com.currencyconverter.data.model.ExchangeRatesResponse

interface ExchangeRateRepository {

    suspend fun getRates(): Result<ExchangeRatesResponse>
}