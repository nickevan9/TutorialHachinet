package com.example.tutorialhachinet.network

import com.example.tutorialhachinet.model.characters.Characters
import com.skydoves.sandwich.ApiResponse

class MarvelClient constructor(private val marvelService: MarvelService) {

    suspend fun fetchCharacterList(
        apiKey: String,
        ts: String,
        hash: String,
        page: Int
    ): ApiResponse<Characters> =
        marvelService.fetchCharacters(
            apiKey,
            ts,
            hash,
            limit = PAGING_SIZE,
            offset = page * PAGING_SIZE
        )

    companion object {
        private const val PAGING_SIZE = 20
    }

}