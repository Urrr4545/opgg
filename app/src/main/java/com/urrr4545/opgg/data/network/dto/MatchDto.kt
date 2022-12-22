package com.urrr4545.opgg.data.network.dto

import com.google.gson.annotations.SerializedName

class MatchDto(
    @SerializedName("games")
    val games: List<GameDto>,

    @SerializedName("champions")
    val champion: List<ChampionDto>,

    @SerializedName("positions")
    val positions: List<PositionDto>
)