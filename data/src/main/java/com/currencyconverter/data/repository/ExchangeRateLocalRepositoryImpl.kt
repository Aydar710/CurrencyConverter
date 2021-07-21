package com.currencyconverter.data.repository

import com.currencyconverter.data.database.dao.ExchangeRateDao
import com.currencyconverter.data.model.local.ExchangeRateLocal
import com.currencyconverter.data.model.local.ExchangeRatesCompound
import com.currencyconverter.data.model.local.ExchangeRatesLocal

class ExchangeRateLocalRepositoryImpl(private val exchangeRateDao: ExchangeRateDao) : ExchangeRateLocalRepository {

    override suspend fun saveExchangeRate(exchangeRateLocal: List<ExchangeRateLocal>) {
        exchangeRateDao.insertExchangeRate(exchangeRateLocal)
    }

    override suspend fun saveExchangeRatesLocal(exchangeRatesLocal: ExchangeRatesLocal): Int {
        return exchangeRateDao.insertExchangeRates(exchangeRatesLocal).toInt()
    }

    override suspend fun getLastRates(): ExchangeRatesCompound? = exchangeRateDao.getLastRates()

    override suspend fun clearDatabase() {
        exchangeRateDao.clear()
    }
}