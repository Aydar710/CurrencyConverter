package com.currencyconverter.domain.interactor

import com.currencyconverter.data.model.local.ExchangeRateLocal
import com.currencyconverter.data.model.local.ExchangeRatesLocal
import com.currencyconverter.data.repository.ExchangeRateLocalRepository
import com.currencyconverter.domain.model.Currencies
import com.currencyconverter.domain.model.ExchangeRate

class SaveRatesToDatabaseInteractor(private val exchangeRateLocalRepository: ExchangeRateLocalRepository) {

    suspend operator fun invoke(currencies: Currencies) {
        val rateHolderId = exchangeRateLocalRepository.saveExhangeRatesLocal(currencies.mapToExchangeRatesLocal())
        val exchangeRates = currencies.exchangeRates.map {
            it.toExchangeRateLocal(rateHolderId)
        }
        exchangeRateLocalRepository.saveExchangeRate(exchangeRates)
    }

    private fun Currencies.mapToExchangeRatesLocal(): ExchangeRatesLocal {
        return ExchangeRatesLocal(
            date = date
        )
    }

    private fun ExchangeRate.toExchangeRateLocal(rateHolderId: Int): ExchangeRateLocal {
        return ExchangeRateLocal(
            id = id,
            numCode = numCode,
            charCode = charCode,
            nominal = nominal,
            name = name,
            value = value,
            rateHolderId = rateHolderId
        )
    }
}
