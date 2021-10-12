package com.example.tutorialhachinet.mapper

import com.example.tutorialhachinet.network.MarvelErrorMapper
import com.skydoves.sandwich.ApiErrorModelMapper
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.message


object ErrorResponseMapper : ApiErrorModelMapper<MarvelErrorMapper> {

  override fun map(apiErrorResponse: ApiResponse.Failure.Error<*>): MarvelErrorMapper {
    return MarvelErrorMapper(apiErrorResponse.statusCode.code, apiErrorResponse.message())
  }
}
