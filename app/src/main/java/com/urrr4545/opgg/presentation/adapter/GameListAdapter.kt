package com.urrr4545.opgg.presentation.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.*
import com.urrr4545.opgg.databinding.ItemGameBinding
import com.urrr4545.opgg.domain.model.Game
import dagger.hilt.android.qualifiers.ApplicationContext

class GameListAdapter(
    private val clicked: (View) -> Unit
): PagingDataAdapter<Game, GameListAdapter.GameViewHolder>(
    GameDiffCallback()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): GameViewHolder {
        return GameViewHolder(
            ItemGameBinding.inflate(
                LayoutInflater.from(parent.context),parent,false
            )
        )
    }

    override fun onBindViewHolder(
        holder: GameViewHolder,
        position: Int) {
        holder.bind(getItem(position))
    }

    inner class GameViewHolder(
        private val binding: ItemGameBinding
    ): RecyclerView.ViewHolder(binding.root) {

        init {
            binding.setClickListener { view ->
                //todo go detail
            }
        }

        fun bind(item: Game?) {
            binding.game = item
        }
    }
}

class GameDiffCallback : DiffUtil.ItemCallback<Game>() {

    override fun areItemsTheSame(
        oldItem: Game,
        newItem: Game
    ): Boolean {
        return oldItem.gameId == newItem.gameId
    }

    override fun areContentsTheSame(
        oldItem: Game,
        newItem: Game
    ): Boolean {
        return oldItem == newItem
    }
}


