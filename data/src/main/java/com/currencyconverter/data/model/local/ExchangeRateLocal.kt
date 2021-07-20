package com.currencyconverter.data.model.local

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [ForeignKey(
        entity = ExchangeRatesLocal::class,
        parentColumns = ["id"],
        childColumns = ["rateHolderId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class ExchangeRateLocal(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val numCode: Int,
    val charCode: String,
    val nominal: Int,
    val name: String,
    val value: Double,
    val rateHolderId: Int
)
