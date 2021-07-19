package com.currencyconverter.data.di

import com.currencyconverter.data.repository.CurrencyRepository
import com.currencyconverter.data.repository.CurrencyRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    factory<CurrencyRepository> { CurrencyRepositoryImpl(get()) }
}