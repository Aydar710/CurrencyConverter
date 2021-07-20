package com.currencyconverter.featuremain.di

import com.currencyconverter.featuremain.presentation.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel(get(), get(), get(), get(), get()) }
}