package com.urrr4545.opgg.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.LoadState
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.urrr4545.opgg.R
import com.urrr4545.opgg.databinding.FragmentMainBinding
import com.urrr4545.opgg.domain.utils.toAnalysis
import com.urrr4545.opgg.presentation.adapter.GameListAdapter
import com.urrr4545.opgg.presentation.adapter.LeagueListAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private val viewModel: MainViewModel by activityViewModels()
    private lateinit var leagueListAdapter: LeagueListAdapter
    private lateinit var gameListAdapter: GameListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        binding.vm = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initObserver()
        handleLoadingStateAdapter()
    }

    private fun initView() {

        binding.viewSummonerProfile.rvLeagues.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            //todo deco

            leagueListAdapter = LeagueListAdapter()
            adapter = leagueListAdapter
        }

        binding.rvGame.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            //val spacingDecorator = SpacingItemDecoration(24)
            //removeItemDecoration(spacingDecorator)
            //addItemDecoration(spacingDecorator)
            gameListAdapter = GameListAdapter() {
                //todo click
            }
            adapter = gameListAdapter

            ResourcesCompat.getDrawable(
                resources,
                R.drawable.item_divider_game,
                requireActivity().theme
            )?.run {
                val divider = DividerItemDecoration(context, LinearLayoutManager.VERTICAL)
                divider.setDrawable(this)
                this@apply.addItemDecoration(divider)
            }
        }

        binding.viewSummonerProfile.btnRefresh.setOnClickListener {
            refresh()
        }
    }

    private fun initObserver() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.summonerUiState.collect { summonerUiState ->
                    summonerUiState.summoner?.let {
                        binding.summoner = it
                        leagueListAdapter.submitList(it.leagues)
                    }
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.gameUiState.collectLatest { gameUiState ->
                    Log.e("vv","gameUiState.games")
                    gameUiState.games?.let {
                        Log.e("vv","submit data")
                        gameListAdapter.submitData(it)
                    }
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.analysisUiState.collect { analysisUiState ->
                    analysisUiState.match?.let {
                        //todo analysis
                        binding.analysis = it.toAnalysis()
                    }
                }
            }
        }
    }

    private fun handleLoadingStateAdapter() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                gameListAdapter.addLoadStateListener { loadState ->
                    if (loadState.source.refresh is LoadState.Loading) {
                        if (gameListAdapter.snapshot().isEmpty()) {
                            //todo start loading
                        }

                        //todo update ui loading
                    } else {
                        //todo stop loading and update ui

                        val error = when {
                            loadState.source.prepend is LoadState.Error -> loadState.source.prepend as LoadState.Error
                            loadState.source.append is LoadState.Error -> loadState.source.append as LoadState.Error
                            loadState.source.refresh is LoadState.Error -> loadState.source.refresh as LoadState.Error
                            else -> null
                        }
                        error?.let {
                            if (gameListAdapter.snapshot().isEmpty()) {
                                //todo show err update ui
                            }
                        }
                    }
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                gameListAdapter.loadStateFlow
                    .distinctUntilChangedBy { it.refresh }
                    .filter { it.refresh is LoadState.NotLoading }
            }
        }
    }

    private fun refresh() {
        viewModel.getSummoner()
        viewModel.getAnalysis()
        gameListAdapter.refresh()
    }
}