package com.jeric.metromart.util

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

inline fun <ResultType, RequestType> networkBoundResource(
    crossinline query: () -> Flow<ResultType>,
    crossinline fetch: suspend () -> RequestType,
    crossinline saveFetchResult: suspend (RequestType) -> Unit,
    crossinline shouldFetch: (ResultType) -> Boolean = { true }
) = flow {
    val data = query().first()
    val flow = if (shouldFetch(data)) {
        emit(DataState.Loading(data))
        try {
            saveFetchResult(fetch())
            query().map { DataState.Success(it) }
        } catch (throwable: Throwable) {
            query().map { DataState.Error(throwable.message ?: "Error", it) }
        }
    } else {
        query().map { DataState.Success(it) }
    }
    emitAll(flow)
}.flowOn(Dispatchers.IO)