package com.currencyconverter.data.repository

import com.currencyconverter.data.model.local.ExchangeRateLocal
import com.currencyconverter.data.model.local.ExchangeRatesCompound
import com.currencyconverter.data.model.local.ExchangeRatesLocal

interface ExchangeRateLocalRepository {

    suspend fun saveExchangeRate(exchangeRateLocal: List<ExchangeRateLocal>)

    suspend fun saveExchangeRatesLocal(exchangeRatesLocal: ExchangeRatesLocal): Int

    suspend fun getLastRates(): ExchangeRatesCompound?

    suspend fun clearDatabase()
}