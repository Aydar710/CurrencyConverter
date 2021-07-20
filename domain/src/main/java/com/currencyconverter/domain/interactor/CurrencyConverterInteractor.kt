package com.currencyconverter.domain.interactor

import java.math.BigDecimal
import java.math.RoundingMode

class CurrencyConverterInteractor {

    operator fun invoke(amount: BigDecimal, fromRate: Double, toRate: Double): BigDecimal {
        val valueInRubles = convertAnyCurrencyToRuble(amount, fromRate)
        return convertRubleToAnyCurrency(valueInRubles, toRate)
    }

    private fun convertAnyCurrencyToRuble(amount: BigDecimal, fromRate: Double): BigDecimal {
        val scale = 50
        return amount.divide(BigDecimal.valueOf(fromRate), scale, RoundingMode.HALF_UP)
    }

    private fun convertRubleToAnyCurrency(rubleValue: BigDecimal, toRate: Double): BigDecimal {
        return rubleValue.multiply(BigDecimal.valueOf(toRate))
    }
}