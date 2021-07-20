package com.currencyconverter.data.repository

import com.currencyconverter.data.model.remote.ExchangeRatesResponse

interface ExchangeRateRepository {

    suspend fun getRates(): Result<ExchangeRatesResponse>
}