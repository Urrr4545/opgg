package com.urrr4545.opgg.domain.usecase

import androidx.paging.PagingData
import com.urrr4545.opgg.domain.model.Game
import com.urrr4545.opgg.domain.model.Match
import com.urrr4545.opgg.domain.model.MatchList
import com.urrr4545.opgg.domain.repository.SummonerRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetGameUseCase
@Inject
constructor(
    private val summonerRepository: SummonerRepository
    ) {

    operator fun invoke(): Flow<PagingData<Game>> = summonerRepository.getMatches()
}