package com.currencyconverter.data.utils

import retrofit2.Response

suspend fun <Data, Call : Response<Data>> makeApiCall(call: suspend () -> Call): Result<Data> = try {
    val madeCall = call()
    if (madeCall.isSuccessful) {
        val body = madeCall.body()
        body?.let { Result.success(it) } ?: Result.failure(Exception("Body is empty"))
    } else {
        Result.failure(Exception("${madeCall.errorBody()}"))
    }
} catch (e: Exception) {
    Result.failure(e)
}