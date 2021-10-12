package com.example.tutorialhachinet.view.ui.character

import androidx.annotation.MainThread
import androidx.databinding.Bindable
import androidx.lifecycle.viewModelScope
import com.example.tutorialhachinet.model.characters.Character
import com.example.tutorialhachinet.repository.CharacterRepository
import com.skydoves.bindables.BindingViewModel
import com.skydoves.bindables.asBindingProperty
import com.skydoves.bindables.bindingProperty
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import timber.log.Timber

class CharacterViewModel constructor(characterRepository: CharacterRepository) : BindingViewModel() {
    @get:Bindable
    var isLoading: Boolean by bindingProperty(false)
        private set

    @get:Bindable
    var toastMessage: String? by bindingProperty(null)
        private set

    private val pokemonFetchingIndex: MutableStateFlow<Int> = MutableStateFlow(0)
    private val pokemonListFlow = pokemonFetchingIndex.flatMapLatest { page ->
        characterRepository.loadDisneyPosters(
            apiKey = "001ac6c73378bbfff488a36141458af2",
            hash = "72e5ed53d1398abb831c3ceec263f18b",
            ts = "thesoer",
            page = page,
            onStart = { isLoading = true },
            onComplete = { isLoading = false },
            onError = { toastMessage = it }
        )
    }

    @get:Bindable
    val characterList: List<Character> by pokemonListFlow.asBindingProperty(viewModelScope, emptyList())

    init {
        Timber.d("init CharacterViewModel")
    }

    @MainThread
    fun fetchNextCharacterList() {
        if (!isLoading) {
            pokemonFetchingIndex.value++
        }
    }
}