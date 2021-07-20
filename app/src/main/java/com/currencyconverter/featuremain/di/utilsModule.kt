package com.currencyconverter.featuremain.di

import com.currencyconverter.featuremain.utils.NetworkConnectionUtils
import org.koin.dsl.module

val utilsModule = module {
    single { NetworkConnectionUtils(get()) }
}