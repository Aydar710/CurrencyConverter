package com.currencyconverter.domain.model

data class ExchangeRate(
    val id: String,
    val numCode: Int,
    val charCode: String,
    val nominal: Int,
    val name: String,
    val value: Double
)