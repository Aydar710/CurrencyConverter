package com.currencyconverter.data.gsonutils

import com.currencyconverter.data.model.Currency
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class GsonValutesDeserializer : JsonDeserializer<List<Currency>> {

    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): List<Currency> {
        val currencies = mutableListOf<Currency>()
        json?.asJsonObject?.let { jsonObject ->
            jsonObject
                .entrySet()
                .map {
                    it.value.asJsonObject
                }
                .forEach { currencyJsonObject ->
                    currencies.add(
                        Currency(
                            id = currencyJsonObject["ID"].asString,
                            numCode = currencyJsonObject["NumCode"].asInt,
                            charCode = currencyJsonObject["CharCode"].asString,
                            nominal = currencyJsonObject["Nominal"].asInt,
                            name = currencyJsonObject["Name"].asString,
                            value = currencyJsonObject["Value"].asDouble
                        )
                    )
                }
        }
        return currencies
    }
}