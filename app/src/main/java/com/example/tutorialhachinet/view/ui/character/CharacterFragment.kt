package com.example.tutorialhachinet.view.ui.character

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tutorialhachinet.R
import com.example.tutorialhachinet.databinding.FragmentCharacterBinding
import com.example.tutorialhachinet.view.adapter.CharacterAdapter
import com.skydoves.bindables.BindingFragment
import org.koin.android.viewmodel.ext.android.viewModel
import timber.log.Timber

class CharacterFragment : BindingFragment<FragmentCharacterBinding>(R.layout.fragment_character) {



    private val viewModel: CharacterViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        return binding {
            adapter = CharacterAdapter {
                Timber.d(" ${it.name}")
            }
            vm = viewModel
        }.root
    }

}