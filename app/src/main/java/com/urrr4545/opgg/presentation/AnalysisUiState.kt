package com.urrr4545.opgg.presentation

import androidx.paging.PagingData
import com.urrr4545.opgg.domain.model.Game
import com.urrr4545.opgg.domain.model.Match

data class AnalysisUiState(
    val isLoading: Boolean = false,
    val match: Match? = null,
    val errorMessage: String? = null
)
