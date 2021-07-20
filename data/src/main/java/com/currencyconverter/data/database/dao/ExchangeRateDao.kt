package com.currencyconverter.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.currencyconverter.data.model.local.ExchangeRateLocal
import com.currencyconverter.data.model.local.ExchangeRatesCompound
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

    @Query("SELECT * FROM ExchangeRatesLocal ORDER BY date ASC LIMIT 1")
    suspend fun getLastRates(): ExchangeRatesCompound

    @Query("DELETE FROM ExchangeRatesLocal")
    suspend fun clear()
}
