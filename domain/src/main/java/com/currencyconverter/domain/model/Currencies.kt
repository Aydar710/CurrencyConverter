package com.currencyconverter.domain.model

import java.util.*

data class Currencies(
    val date: Date,
    val currencies: List<Currency>
)