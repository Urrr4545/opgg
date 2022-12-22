package com.urrr4545.opgg.data.network.response

import com.google.gson.annotations.SerializedName
import com.urrr4545.opgg.data.network.dto.SummonerDto

data class SummonerResponse(
    @SerializedName("summoner")
    val summoner: SummonerDto
)