package com.currencyconverter.domain.interactor

import com.currencyconverter.data.model.CurrenciesResponse
import com.currencyconverter.data.model.Currency
import com.currencyconverter.data.repository.CurrencyRepository
import com.currencyconverter.domain.model.Currencies

class GetTodayCurrenciesInteractor(private val currencyRepository: CurrencyRepository) {

    suspend operator fun invoke(): Result<Currencies> {
        val currenciesResult = currencyRepository.getTodayCurrencies()
        return currenciesResult.getOrNull()?.let { successCurrenciesResult ->
            Result.success(successCurrenciesResult.toDomainCurrencies())
        } ?: Result.failure(currenciesResult.exceptionOrNull() ?: Exception("Exception of result is null"))
    }
}

private fun CurrenciesResponse.toDomainCurrencies(): Currencies {
    val domainCurrencies = currencies.map { it.toDomainCurrency() }
    return Currencies(
        date = date,
        currencies = domainCurrencies
    )
}

private fun Currency.toDomainCurrency(): com.currencyconverter.domain.model.Currency {
    return com.currencyconverter.domain.model.Currency(
        id = id,
        numCode = numCode,
        charCode = charCode,
        nominal = nominal,
        name = name,
        value = value
    )
}