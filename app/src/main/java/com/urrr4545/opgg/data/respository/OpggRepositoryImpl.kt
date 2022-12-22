/*
 * Copyright 2020 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.urrr4545.opgg.data.respository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.urrr4545.opgg.data.network.OpggApi
import com.urrr4545.opgg.data.network.dto.GameDto
import com.urrr4545.opgg.data.network.dto.GameDtoMapper
import com.urrr4545.opgg.data.network.dto.SummonerDtoMapper
import com.urrr4545.opgg.data.network.response.MatchResponse
import com.urrr4545.opgg.domain.model.Game
import com.urrr4545.opgg.domain.model.Match
import com.urrr4545.opgg.domain.model.MatchList
import com.urrr4545.opgg.domain.model.Summoner
import com.urrr4545.opgg.domain.repository.SummonerRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class OpggRepositoryImpl @Inject constructor(private val api: OpggApi,
                                             private val summonerDtoMapper: SummonerDtoMapper,
                                             private val matchDtoMapper: GameDtoMapper
): SummonerRepository {

    override suspend fun getSummoner(): Summoner {
        return summonerDtoMapper
            .mapToDomainModel(
                api.getSummoner().summoner
            )
    }

    override suspend fun getAnalysis(): Match {
        return matchDtoMapper
            .toMatchData(
                api.getMatches()
            )
    }

    override fun getMatches(): Flow<PagingData<Game>> {
        return Pager(
            config = PagingConfig(30),
            pagingSourceFactory = { GamePagingSource(api, matchDtoMapper) }
        ).flow
    }

    companion object {

    }
}
