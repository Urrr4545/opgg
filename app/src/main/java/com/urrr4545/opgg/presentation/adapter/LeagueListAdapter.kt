package com.urrr4545.opgg.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.urrr4545.opgg.R
import com.urrr4545.opgg.databinding.ItemLeagueBinding
import com.urrr4545.opgg.domain.model.League


class LeagueListAdapter :
    ListAdapter<League, LeagueListAdapter.ViewHolder>(
        LeagueDiffCallback()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_league,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(
        private val binding: ItemLeagueBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.setClickListener { view ->
                //todo
            }
        }

        fun bind(item: League) {
            with(binding) {
                league = item
            }
        }
    }
}

private class LeagueDiffCallback : DiffUtil.ItemCallback<League>() {

    override fun areItemsTheSame(
        oldItem: League,
        newItem: League
    ): Boolean {
        return oldItem.tierRank.lp == newItem.tierRank.lp
    }

    override fun areContentsTheSame(
        oldItem: League,
        newItem: League
    ): Boolean {
        return oldItem == newItem
    }
}

