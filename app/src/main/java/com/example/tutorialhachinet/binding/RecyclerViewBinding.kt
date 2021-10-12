package com.example.tutorialhachinet.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.tutorialhachinet.view.ui.character.CharacterViewModel
import com.skydoves.baserecyclerviewadapter.RecyclerViewPaginator
import com.skydoves.bindables.BindingListAdapter

object RecyclerViewBinding {

    @JvmStatic
    @BindingAdapter("adapter")
    fun bindAdapter(view: RecyclerView, adapter: RecyclerView.Adapter<*>) {
        view.adapter = adapter.apply {
            stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
        }
    }

    @JvmStatic
    @BindingAdapter("submitList")
    fun bindSubmitList(view: RecyclerView, itemList: List<Any>?) {
        (view.adapter as BindingListAdapter<Any, *>).submitList(itemList)
    }

    @JvmStatic
    @BindingAdapter("paginationPokemonList")
    fun paginationMarvelList(view: RecyclerView, viewModel: CharacterViewModel) {
        RecyclerViewPaginator(
            recyclerView = view,
            isLoading = { viewModel.isLoading },
            loadMore = {
                viewModel.fetchNextCharacterList()
                       },
            onLast = { false }
        ).run {
            threshold = 4
        }
    }
}