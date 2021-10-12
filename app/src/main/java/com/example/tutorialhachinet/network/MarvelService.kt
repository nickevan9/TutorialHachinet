package com.example.tutorialhachinet.network

import com.example.tutorialhachinet.model.characters.Characters
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelService {

  @GET("characters")
  suspend fun fetchCharacters(
    @Query("apiKey") apiKey : String,
    @Query("ts") ts : String,
    @Query("hash") hash : String,
    @Query("limit") limit: Int = 20,
    @Query("offset") offset: Int = 0
  ): ApiResponse<Characters>
}
