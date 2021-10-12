package com.example.tutorialhachinet.di

import com.example.tutorialhachinet.view.ui.character.CharacterViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

  viewModel { CharacterViewModel(get()) }
}
