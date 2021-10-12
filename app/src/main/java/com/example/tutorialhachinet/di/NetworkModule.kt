package com.example.tutorialhachinet.di

import com.example.tutorialhachinet.network.MarvelClient
import com.example.tutorialhachinet.network.MarvelService
import com.example.tutorialhachinet.network.RequestInterceptor
import com.skydoves.sandwich.coroutines.CoroutinesResponseCallAdapterFactory
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {

  single {
    OkHttpClient.Builder()
      .addInterceptor(RequestInterceptor())
      .build()
  }

  single {
    Retrofit.Builder()
      .client(get<OkHttpClient>())
      .baseUrl(
        "https://gateway.marvel.com/v1/public/"
      )
      .addConverterFactory(GsonConverterFactory.create())
      .addCallAdapterFactory(CoroutinesResponseCallAdapterFactory.create())
      .build()
  }

  single { get<Retrofit>().create(MarvelService::class.java) }

  single { (marvelService: MarvelService) -> MarvelClient(marvelService) }
}
