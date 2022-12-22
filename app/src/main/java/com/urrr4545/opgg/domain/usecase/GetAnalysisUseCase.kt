package com.urrr4545.opgg.domain.usecase

import android.util.Log
import androidx.paging.PagingData
import com.urrr4545.opgg.domain.model.Game
import com.urrr4545.opgg.domain.model.Match
import com.urrr4545.opgg.domain.model.Summoner
import com.urrr4545.opgg.domain.repository.SummonerRepository
import com.urrr4545.opgg.domain.utils.OperationResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAnalysisUseCase
@Inject
constructor(
    private val summonerRepository: SummonerRepository
    ) {

    operator fun invoke(): Flow<OperationResult<Match>>
    = flow {
        try {
            emit(OperationResult.Loading())
            val match = summonerRepository.getAnalysis()
            emit(OperationResult.Success(match))
        } catch (e: Exception) {
            emit(OperationResult.Error("${e.message}"))
        }
    }
}