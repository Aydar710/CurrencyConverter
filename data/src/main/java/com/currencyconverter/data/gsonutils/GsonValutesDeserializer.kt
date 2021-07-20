package com.currencyconverter.data.gsonutils

import com.currencyconverter.data.model.remote.ExchangeRate
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class GsonValutesDeserializer : JsonDeserializer<List<ExchangeRate>> {

    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): List<ExchangeRate> {
        val currencies = mutableListOf<ExchangeRate>()
        json?.asJsonObject?.let { jsonObject ->
            jsonObject
                .entrySet()
                .map {
                    it.value.asJsonObject
                }
                .forEach { currencyJsonObject ->
                    currencies.add(
                        ExchangeRate(
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