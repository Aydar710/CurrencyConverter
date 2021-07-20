package com.currencyconverter.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.currencyconverter.data.model.local.ExchangeRateLocal
import com.currencyconverter.data.model.local.ExchangeRatesLocal

@Dao
interface ExchangeRateDao {

    /*@Transaction
    suspend fun saveExchangeRatesCompound(vararg exchangeRatesCompound: ExchangeRatesCompound) {
        *//*exchangeRatesCompound.forEach { exchangeRateCompound ->
            insertExchangeRates(exchangeRateCompound.exchangeRatesLocal)
            exchangeRateCompound.rates.forEach {
                insertExchangeRate(it)
            }
        }*//*
    }*/

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExchangeRate(exchangeRateLocal: List<ExchangeRateLocal>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExchangeRates(exchangeRatesLocal: ExchangeRatesLocal): Long
}
