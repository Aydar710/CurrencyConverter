package com.currencyconverter.data.service

import com.currencyconverter.data.model.CurrenciesResponse
import retrofit2.Response
import retrofit2.http.GET

interface CurrenciesService {
    @GET("daily_json.js")
    suspend fun getTodayCurrencyValues(): Response<CurrenciesResponse>
}