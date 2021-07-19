package com.currencyconverter.domain.di

import com.currencyconverter.domain.interactor.GetTodayCurrenciesInteractor
import org.koin.dsl.module

val interactorModule = module {
    factory { GetTodayCurrenciesInteractor(get()) }
}