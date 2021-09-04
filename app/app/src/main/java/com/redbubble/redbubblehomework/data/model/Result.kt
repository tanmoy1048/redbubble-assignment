package com.redbubble.redbubblehomework.data.model

/**
 * Generic class for holding success response, error response and loading status
 */

sealed class Result<out T> {
    class Success<out T>(val data: T?) : Result<T>()
    class Failure<out T>(val message: String) : Result<T>()
    class Loading<out T> : Result<T>()
}