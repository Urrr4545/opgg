package com.urrr4545.opgg.presentation

import android.util.Log
import androidx.lifecycle.*
import androidx.paging.cachedIn
import androidx.paging.flatMap
import com.urrr4545.opgg.domain.usecase.GetAnalysisUseCase
import com.urrr4545.opgg.domain.usecase.GetGameUseCase
import com.urrr4545.opgg.domain.usecase.GetSummonerUseCase
import com.urrr4545.opgg.domain.utils.OperationResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getSummonerUseCase: GetSummonerUseCase,
    private val getGameUseCase: GetGameUseCase,
    private val getAnalysisUseCase: GetAnalysisUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _summonerUiState = MutableStateFlow(SummonerUiState())
    val summonerUiState: StateFlow<SummonerUiState> = _summonerUiState.asStateFlow()

    private val _gameUiState = MutableStateFlow(GameUiState())
    val gameUiState: StateFlow<GameUiState> = _gameUiState.asStateFlow()

    private val _analysisUiState = MutableStateFlow(AnalysisUiState())
    val analysisUiState: StateFlow<AnalysisUiState> = _analysisUiState.asStateFlow()

    init {
        getSummoner()
        getGames()
        getAnalysis()
    }

    fun getGames() {
        viewModelScope.launch {
            delay(1000)
            getGameUseCase()
                .cachedIn(viewModelScope)
                .collect { game ->
                    _gameUiState.update { it.copy(games = game) }
                }
        }
    }

    fun getAnalysis() {
        viewModelScope.launch {
            getAnalysisUseCase()
                .onEach {
                        dataState ->
                    when(dataState) {
                        is OperationResult.Loading -> {
                            _analysisUiState.update { it.copy(isLoading = true) }
                        }
                        is OperationResult.Success -> {
                            _analysisUiState.update { it.copy(isLoading = false) }
                            _analysisUiState.update { it.copy(errorMessage = null) }
                            dataState.data?.let { match ->
                                _analysisUiState.update { it.copy(match = match) }
                            }
                        }
                        is OperationResult.Error -> {
                            _analysisUiState.update { it.copy(isLoading = false) }
                            _analysisUiState.update { it.copy(errorMessage = dataState.message) }
                        }
                    }
                }.launchIn(viewModelScope)
        }
    }

    fun getSummoner() {
        viewModelScope.launch {
            getSummonerUseCase()
                .onEach {
                        dataState ->
                    when(dataState) {
                        is OperationResult.Loading -> {
                            _summonerUiState.update { it.copy(isLoading = true) }
                        }
                        is OperationResult.Success -> {
                            _summonerUiState.update { it.copy(isLoading = false) }
                            _summonerUiState.update { it.copy(errorMessage = null) }
                            dataState.data?.let { summoner ->
                                _summonerUiState.update { it.copy(summoner = summoner) }
                            }
                        }
                        is OperationResult.Error -> {
                            _summonerUiState.update { it.copy(isLoading = false) }
                            _summonerUiState.update { it.copy(errorMessage = dataState.message) }
                        }
                    }
                }.launchIn(viewModelScope)
        }
    }


    private fun refresh() {

    }
}