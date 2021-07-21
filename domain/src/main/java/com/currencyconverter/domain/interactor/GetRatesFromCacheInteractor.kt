package com.currencyconverter.domain.interactor

import com.currencyconverter.data.model.local.ExchangeRateLocal
import com.currencyconverter.data.model.local.ExchangeRatesCompound
import com.currencyconverter.data.repository.ExchangeRateLocalRepository
import com.currencyconverter.domain.model.Currencies
import com.currencyconverter.domain.model.ExchangeRate

class GetRatesFromCacheInteractor(private val exchangeRateLocalRepository: ExchangeRateLocalRepository) {

    suspend operator fun invoke(): Currencies? {
        return exchangeRateLocalRepository.getLastRates()?.toDomainCurrencies()
    }

    private fun ExchangeRatesCompound.toDomainCurrencies(): Currencies {
        return Currencies(
            date = exchangeRatesLocal.date,
            exchangeRates = rates.map { it.toDomainExchangeRate() }
        )
    }

    private fun ExchangeRateLocal.toDomainExchangeRate(): ExchangeRate {
        return ExchangeRate(
            id = id,
            numCode = numCode,
            charCode = charCode,
            nominal = nominal,
            name = name,
            value = value
        )
    }
}
