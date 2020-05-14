package com.gmail.evgenpatiy.gloice.api

sealed class RestApiResult<out T : Any> {

    data class SuccessCall<out T : Any>(val data: T) : RestApiResult<T>()
    data class FailedCall(val exception: Exception) : RestApiResult<Nothing>()
}