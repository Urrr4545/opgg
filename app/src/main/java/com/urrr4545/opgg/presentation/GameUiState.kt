package com.urrr4545.opgg.presentation

import androidx.paging.PagingData
import com.urrr4545.opgg.domain.model.Game
import com.urrr4545.opgg.domain.model.Match

data class GameUiState(
    val isLoading: Boolean = false,
    val games: PagingData<Game>? = null,
    val errorMessage: String? = null
)
