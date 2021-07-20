package com.currencyconverter.data.repository

import com.currencyconverter.data.database.dao.ExchangeRateDao

class ExchangeRateLocalRepositoryImpl(private val exchangeRateDao: ExchangeRateDao) : ExchangeRateLocalRepository {

    override suspend fun saveExchangeRate() {}
}