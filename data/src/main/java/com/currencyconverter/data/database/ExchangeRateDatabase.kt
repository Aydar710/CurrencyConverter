package com.currencyconverter.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.currencyconverter.data.database.dao.ExchangeRateDao
import com.currencyconverter.data.database.typeconverters.DateTypeConverter
import com.currencyconverter.data.model.local.ExchangeRateLocal
import com.currencyconverter.data.model.local.ExchangeRatesLocal

@Database(
    entities = [ExchangeRatesLocal::class, ExchangeRateLocal::class],
    exportSchema = false,
    version = 1
)
@TypeConverters(
    value = [
        DateTypeConverter::class
    ]
)
abstract class ExchangeRateDatabase : RoomDatabase() {

    companion object {
        private const val DATABASE_NAME = "ExchangeRateDatabase"
        fun getDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            ExchangeRateDatabase::class.java,
            DATABASE_NAME
        ).build()
    }

    abstract fun exchangeRateDao(): ExchangeRateDao
}