package com.currencyconverter.domain.di

import com.currencyconverter.domain.interactor.*
import org.koin.dsl.module

val interactorModule = module {

    factory { GetTodayCurrenciesInteractor(get()) }
    factory { CurrencyConverterInteractor() }
    factory { SaveRatesToDatabaseInteractor(get()) }
    factory { SyncRatesInteractor(get(), get()) }
    factory { GetRatesFromCacheInteractor(get()) }
}