package com.currencyconverter.domain.interactor

class SyncRatesInteractor(
    private val getTodayCurrenciesInteractor: GetTodayCurrenciesInteractor,
    private val saveRatesToDatabaseInteractor: SaveRatesToDatabaseInteractor
) {

    suspend operator fun invoke(): Result<Unit> {
        return getTodayCurrenciesInteractor.invoke().getOrNull()?.let { currencies ->
            saveRatesToDatabaseInteractor.invoke(currencies)
            Result.success(Unit)
        } ?: Result.failure(Exception("could not get currencies"))
    }
}
