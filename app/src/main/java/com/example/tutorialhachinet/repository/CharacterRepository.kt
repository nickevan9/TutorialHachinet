package com.example.tutorialhachinet.repository

import androidx.annotation.WorkerThread
import com.example.tutorialhachinet.mapper.ErrorResponseMapper
import com.example.tutorialhachinet.network.MarvelClient
import com.skydoves.sandwich.map
import com.skydoves.sandwich.onError
import com.skydoves.sandwich.onException
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import timber.log.Timber

class CharacterRepository constructor(private val marvelClient: MarvelClient) {


    init {
        Timber.d("Injection CharacterRepository")
    }

    @WorkerThread
    fun loadDisneyPosters(
        apiKey: String,
        ts: String,
        hash: String,
        page: Int,
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ) = flow {

        // request API network request asynchronously.
        marvelClient.fetchCharacterList(apiKey, ts, hash, page)
            // handles the success case when the API request gets a successful response.
            .suspendOnSuccess {
                emit(data.data.characters)
            }
            .onError {
                map(ErrorResponseMapper) { onError("[Code: $code]: $message") }
            }
            // handles exceptional cases when the API request gets an exception response.
            // e.g., network connection error.
            .onException {
                Timber.d(message)
            }

    }.onStart { onStart() }.onCompletion { onComplete() }.flowOn(Dispatchers.IO)

}