package com.example.tutorialhachinet.view.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.tutorialhachinet.R
import com.example.tutorialhachinet.databinding.ItemCharacterBinding
import com.example.tutorialhachinet.model.characters.Character
import com.skydoves.bindables.BindingListAdapter
import com.skydoves.bindables.binding

class CharacterAdapter(private val onClick : (Character) -> Unit) : BindingListAdapter<Character, CharacterAdapter.CharacterViewHolder>(diffUtil) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder =
        parent.binding<ItemCharacterBinding>(R.layout.item_character).let(::CharacterViewHolder)

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) =
        holder.bindMarvel(getItem(position))

    inner class CharacterViewHolder constructor(
        private val binding: ItemCharacterBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bindMarvel(character: Character) {
            binding.character = character
            binding.executePendingBindings()

            binding.root.setOnClickListener {
                onClick.invoke(character)
            }
        }
    }

    companion object {
        private val diffUtil = object : DiffUtil.ItemCallback<Character>() {

            override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean =
                oldItem.name == newItem.name

            override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean =
                oldItem == newItem
        }
    }
}