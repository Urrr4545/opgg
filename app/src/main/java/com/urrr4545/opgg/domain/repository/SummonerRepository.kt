package com.urrr4545.opgg.domain.repository

import androidx.paging.PagingData
import com.urrr4545.opgg.domain.model.Game
import com.urrr4545.opgg.domain.model.Match
import com.urrr4545.opgg.domain.model.Summoner
import kotlinx.coroutines.flow.Flow

interface SummonerRepository {
    fun getMatches(): Flow<PagingData<Game>>
    suspend fun getAnalysis(): Match
    suspend fun getSummoner(): Summoner
}