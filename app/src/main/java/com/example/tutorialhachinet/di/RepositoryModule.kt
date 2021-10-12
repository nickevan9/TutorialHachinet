package com.example.tutorialhachinet.di

import com.example.tutorialhachinet.repository.CharacterRepository
import org.koin.dsl.module

val repositoryModule = module {
  single { CharacterRepository(get()) }
}
