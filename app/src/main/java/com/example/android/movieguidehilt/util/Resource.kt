package com.example.android.movieguidehilt.util


typealias SimpleResource = Resource<Unit>

sealed class Resource<T>(val data: T? = null, val message: String? = null) {
    class Loading<T>(data: T? = null): Resource<T>(data)
    class Success<T>(data: T?): Resource<T>(data)
    class Error<T>(message: String, data: T? = null): Resource<T>(data, message )
}


//sealed class Resource<T> {
//    class Success<T>(val data: T) : Resource<T>()
//    class Failed<T>(val message: String) : Resource<T>()
//}