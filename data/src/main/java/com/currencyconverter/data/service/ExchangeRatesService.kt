package com.currencyconverter.data.service

import com.currencyconverter.data.model.ExchangeRatesResponse
import retrofit2.Response
import retrofit2.http.GET

interface ExchangeRatesService {
    @GET("daily_json.js")
    suspend fun getTodayCurrencyValues(): Response<ExchangeRatesResponse>
}