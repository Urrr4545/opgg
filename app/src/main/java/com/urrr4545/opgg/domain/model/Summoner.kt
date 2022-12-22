package com.urrr4545.opgg.domain.model

data class Summoner(
    val name: String,
    val level: String,
    val profileImageUrl: String,
    val leagues: List<League>,
)

data class League(
    val wins: Int,
    val losses: Int,
    val tierRank: TierRank
)

data class TierRank(
    val name: String,
    val imageUrl: String,
    val tier: String,
    val lp: Int,
    val tierRankPoint: Int
)