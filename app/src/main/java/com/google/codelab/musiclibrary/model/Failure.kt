package com.google.codelab.musiclibrary.model

import com.google.codelab.musiclibrary.R
import com.squareup.moshi.JsonDataException
import java.net.UnknownHostException

data class Failure(
    val message: FailureType,
    val retry: () -> Unit
)

enum class FailureType(val message: Int) {
    NetworkError(R.string.offline_error),
    UnexpectedError(R.string.unexpected_error),
    NotFoundError(R.string.not_fond_error)
}

fun getMessage(e: Throwable): FailureType {
    return when (e) {
        is UnknownHostException -> FailureType.NetworkError
        is JsonDataException -> FailureType.NotFoundError
        else -> FailureType.UnexpectedError
    }
}

