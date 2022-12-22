package com.urrr4545.opgg.data.network.response

import com.google.gson.annotations.SerializedName
import com.urrr4545.opgg.data.network.dto.*

data class MatchResponse(
    @SerializedName("games")
    val games: List<GameDto>,

    @SerializedName("champions")
    val champion: List<ChampionDto>,

    @SerializedName("positions")
    val positions: List<PositionDto>

)
