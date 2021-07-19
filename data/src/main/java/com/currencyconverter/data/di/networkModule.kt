package com.currencyconverter.data.di

import com.currencyconverter.data.BuildConfig
import com.currencyconverter.data.gsonutils.GsonDateDeserializer
import com.currencyconverter.data.gsonutils.GsonDateSerializer
import com.currencyconverter.data.service.CurrenciesService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

val networkModule = module {
    single { provideOkHttpClient() }
    single { provideRetrofit(get(), get()) }
    single { provideGson() }
    single { get<Retrofit>().create(CurrenciesService::class.java) }
}

private fun provideRetrofit(
    okHttpClient: OkHttpClient,
    gson: Gson
): Retrofit {
    return Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl(BuildConfig.API_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()
}

private fun provideOkHttpClient(): OkHttpClient {
    return with(OkHttpClient.Builder()) {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        addInterceptor(loggingInterceptor)
        build()
    }
}

private fun provideGson(): Gson {
    return GsonBuilder()
        .registerTypeAdapter(Date::class.java, GsonDateDeserializer())
        .registerTypeAdapter(Date::class.java, GsonDateSerializer())
        .serializeNulls()
        .create()
}