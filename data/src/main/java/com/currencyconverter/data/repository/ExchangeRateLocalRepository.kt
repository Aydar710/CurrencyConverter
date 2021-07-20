package com.currencyconverter.data.repository

interface ExchangeRateLocalRepository {

    suspend fun saveExchangeRate()
}