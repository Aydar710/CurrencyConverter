package com.currencyconverter.domain.interactor

import com.currencyconverter.data.model.remote.ExchangeRate
import com.currencyconverter.data.model.remote.ExchangeRatesResponse
import com.currencyconverter.data.repository.ExchangeRateRepository
import com.currencyconverter.domain.model.Currencies

class GetTodayCurrenciesInteractor(private val exchangeRateRepository: ExchangeRateRepository) {

    suspend operator fun invoke(): Result<Currencies> {
        val currenciesResult = exchangeRateRepository.getRates()
        return currenciesResult.getOrNull()?.let { successCurrenciesResult ->
            Result.success(successCurrenciesResult.toDomainCurrencies())
        } ?: Result.failure(currenciesResult.exceptionOrNull() ?: Exception("Exception of result is null"))
    }
}

private fun ExchangeRatesResponse.toDomainCurrencies(): Currencies {
    val domainCurrencies = exchangeRates.map { it.toDomainCurrency() }
    return Currencies(
        date = date,
        exchangeRates = domainCurrencies
    )
}

private fun ExchangeRate.toDomainCurrency(): com.currencyconverter.domain.model.ExchangeRate {
    return com.currencyconverter.domain.model.ExchangeRate(
        id = id,
        numCode = numCode,
        charCode = charCode,
        nominal = nominal,
        name = name,
        value = value
    )
}