package com.urrr4545.opgg.data.network.dto

import com.google.gson.annotations.SerializedName

class ChampionDto(
    @SerializedName("id") val id: Long,
    @SerializedName("key") val key: String,
    @SerializedName("name") val name: String,
    @SerializedName("imageUrl") val imageUrl: String,
    @SerializedName("games") val games: Long,
    @SerializedName("kills") val kills: Long,
    @SerializedName("deaths") val deaths: Long,
    @SerializedName("assists") val assists: Long,
    @SerializedName("wins") val wins: Long,
    @SerializedName("losses") val losses: Long
)