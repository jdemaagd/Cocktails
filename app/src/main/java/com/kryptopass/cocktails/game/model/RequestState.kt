package com.kryptopass.cocktails.game.model

sealed class RequestState<T> {
    class Loading<T> : RequestState<T>()
    class Success<T>(val requestObject: T) : RequestState<T>()
    class Error<T>(
        val message: String = "",
        val response: String = "",
        val responseCode: Int = -1
    ) :
        RequestState<T>()
}
