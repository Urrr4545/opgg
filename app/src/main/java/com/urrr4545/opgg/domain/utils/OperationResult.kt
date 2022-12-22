package com.urrr4545.opgg.domain.utils

sealed class OperationResult<out T>(
    val data: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T) : OperationResult<T>(data)
    class Error<T>(message: String, data: T? = null) : OperationResult<T>(data, message)
    class Loading<T>(data: T? = null) : OperationResult<T>(data)
}
