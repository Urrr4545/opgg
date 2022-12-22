package com.urrr4545.opgg.data.network.dto

import com.google.gson.annotations.SerializedName

class PositionDto(
    @SerializedName("games") val games: Long,
    @SerializedName("wins") val wins: Long,
    @SerializedName("losses") val losses: Long,
    @SerializedName("position") val position: String,
    @SerializedName("positionName") val positionName: String
)