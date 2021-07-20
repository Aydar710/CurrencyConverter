package com.currencyconverter.domain.model

import java.util.*

data class Currencies(
    val date: Date,
    val exchangeRates: List<ExchangeRate>
)