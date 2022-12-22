package com.urrr4545.opgg.data.network.dto

import com.google.gson.annotations.SerializedName

class SummonerDto(
    @SerializedName("name") val name: String,
    @SerializedName("level") val level: String,
    @SerializedName("profileImageUrl") val profileImageUrl: String,
    @SerializedName("leagues") val leagues: List<LeagueDto>,
)

class LeagueDto(
    @SerializedName("wins") val wins: Int,
    @SerializedName("losses") val losses: Int,
    @SerializedName("tierRank") val tierRank: TierRankDto
)

class TierRankDto(
    @SerializedName("name") val name: String,
    @SerializedName("imageUrl") val imageUrl: String,
    @SerializedName("tier") val tier: String,
    @SerializedName("lp") val lp: Int,
    @SerializedName("tierRankPoint") val tierRankPoint: Int
)