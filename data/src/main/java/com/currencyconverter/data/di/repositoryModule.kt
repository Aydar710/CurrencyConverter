package com.currencyconverter.data.di

import com.currencyconverter.data.repository.ExchangeRateLocalRepository
import com.currencyconverter.data.repository.ExchangeRateLocalRepositoryImpl
import com.currencyconverter.data.repository.ExchangeRateRepository
import com.currencyconverter.data.repository.ExchangeRateRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    factory<ExchangeRateRepository> { ExchangeRateRepositoryImpl(get()) }
    factory<ExchangeRateLocalRepository> { ExchangeRateLocalRepositoryImpl(get()) }
}