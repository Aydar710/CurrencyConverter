package com.currencyconverter.domain.di

import com.currencyconverter.domain.interactor.CurrencyConverterInteractor
import com.currencyconverter.domain.interactor.GetTodayCurrenciesInteractor
import com.currencyconverter.domain.interactor.SaveRatesToDatabaseInteractor
import com.currencyconverter.domain.interactor.SyncRatesInteractor
import org.koin.dsl.module

val interactorModule = module {

    factory { GetTodayCurrenciesInteractor(get()) }
    factory { CurrencyConverterInteractor() }
    factory { SaveRatesToDatabaseInteractor(get()) }
    factory { SyncRatesInteractor(get(), get()) }
}