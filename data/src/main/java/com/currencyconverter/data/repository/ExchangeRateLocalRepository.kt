package com.currencyconverter.data.repository

import com.currencyconverter.data.model.local.ExchangeRateLocal
import com.currencyconverter.data.model.local.ExchangeRatesLocal

interface ExchangeRateLocalRepository {

    suspend fun saveExchangeRate(exchangeRateLocal: List<ExchangeRateLocal>)
    suspend fun saveExhangeRatesLocal(exchangeRatesLocal: ExchangeRatesLocal): Int
}