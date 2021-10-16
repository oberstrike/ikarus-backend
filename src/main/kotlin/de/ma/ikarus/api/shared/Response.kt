package de.ma.ikarus.api.shared

sealed class Response<T> {

    data class Success<T>(val value: T): Response<T>()

    data class Failure<T>(val throwable: Throwable): Response<T>()

}