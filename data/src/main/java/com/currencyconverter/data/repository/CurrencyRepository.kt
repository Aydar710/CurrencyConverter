package com.currencyconverter.data.repository

import com.currencyconverter.data.model.CurrenciesResponse

interface CurrencyRepository {

    suspend fun getTodayCurrencies(): Result<CurrenciesResponse>
}