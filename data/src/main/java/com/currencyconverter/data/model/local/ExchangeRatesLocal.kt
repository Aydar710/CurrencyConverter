package com.currencyconverter.data.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class ExchangeRatesLocal(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val date: Date
)
