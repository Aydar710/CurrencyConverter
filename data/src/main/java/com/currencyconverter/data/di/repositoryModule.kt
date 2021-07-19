package com.currencyconverter.data.di

import com.currencyconverter.data.repository.CurrencyRepository
import org.koin.dsl.module

val repositoryModule = module {
    factory { CurrencyRepository(get()) }
}