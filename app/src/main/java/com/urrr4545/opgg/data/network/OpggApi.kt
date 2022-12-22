package com.urrr4545.opgg.data.network

import com.urrr4545.opgg.data.network.response.MatchResponse
import com.urrr4545.opgg.data.network.response.SummonerResponse
import com.urrr4545.opgg.domain.model.Match
import retrofit2.http.GET
import retrofit2.http.Query

interface OpggApi {

    @GET("api/summoner/genetory")
    suspend fun getSummoner(
    ): SummonerResponse

    @GET("api/summoner/genetory/matches")
    suspend fun getMatches(
        @Query("createDate") createDate: Long? = System.currentTimeMillis()
    ): MatchResponse
}