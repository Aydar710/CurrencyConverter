package com.currencyconverter.data.model.local

import androidx.room.Embedded
import androidx.room.Relation

data class ExchangeRatesCompound(
    @Embedded
    val exchangeRatesLocal: ExchangeRatesLocal,
    @Relation(
        parentColumn = "id",
        entityColumn = "rateHolderId"
    )
    val rates: List<ExchangeRateLocal>
)
