package com.currencyconverter.data.di

import com.currencyconverter.data.database.ExchangeRateDatabase
import org.koin.dsl.module

val databaseModule = module {

    single { ExchangeRateDatabase.getDatabase(get()) }
    single { get<ExchangeRateDatabase>().exchangeRateDao() }
}