package com.urrr4545.opgg.presentation

import com.urrr4545.opgg.domain.model.Summoner

data class SummonerUiState(
    val isLoading: Boolean = false,
    val summoner: Summoner? = null,
    val errorMessage: String? = null
)
