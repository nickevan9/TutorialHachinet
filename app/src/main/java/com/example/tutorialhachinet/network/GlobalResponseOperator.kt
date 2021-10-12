package com.example.tutorialhachinet.network

import android.app.Application
import android.widget.Toast
import com.example.tutorialhachinet.mapper.ErrorResponseMapper
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.StatusCode
import com.skydoves.sandwich.map
import com.skydoves.sandwich.message
import com.skydoves.sandwich.operators.ApiResponseSuspendOperator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

class GlobalResponseOperator<T> constructor(
  private val application: Application
) : ApiResponseSuspendOperator<T>() {

  override suspend fun onError(apiResponse: ApiResponse.Failure.Error<T>) =
    withContext(Dispatchers.Main) {
      apiResponse.run {
        Timber.d(message())

        when (statusCode) {
          StatusCode.InternalServerError -> toast("InternalServerError")
          StatusCode.BadGateway -> toast("BadGateway")
          StatusCode.Conflict -> toast("Query Params Error")

          else -> toast("$statusCode(${statusCode.code}): ${message()}")
        }

        map(ErrorResponseMapper) {
          Timber.d(message())
        }
      }
    }

  override suspend fun onException(apiResponse: ApiResponse.Failure.Exception<T>) =
    withContext(Dispatchers.Main) {
      apiResponse.run {
        Timber.d(message())
        toast(message())
      }
    }

  override suspend fun onSuccess(apiResponse: ApiResponse.Success<T>) = Unit

  private fun toast(message: String) {
    Toast.makeText(application, message, Toast.LENGTH_SHORT).show()
  }
}
