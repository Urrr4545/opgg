package com.urrr4545.opgg.data.network.dto

import com.google.gson.annotations.SerializedName

class GameDto(
    @SerializedName("mmr") val mmr: Long,
    @SerializedName("champion") val champion: GameChampionDto,
    @SerializedName("spells") val spells: List<SpellDto>,
    @SerializedName("items") val items: List<ItemDto>,
    @SerializedName("needRenew") val needRenew: Boolean,
    @SerializedName("gameId") val gameId: String,
    @SerializedName("createDate") val createDate: Long?,
    @SerializedName("gameLength") val gameLength: Long,
    @SerializedName("gameType") val gameType: String,
    @SerializedName("summonerId") val summonerId: String,
    @SerializedName("summonerName") val summonerName: String,
    @SerializedName("tierRankShort") val tierRankShort: String,
    @SerializedName("stats") val stats: StatsDto,
    @SerializedName("peak") val peak: List<String>,
    @SerializedName("isWin") val isWin: Boolean
)

class GameChampionDto(
    @SerializedName("imageUrl") val imageUrl: String,
    @SerializedName("level") val level: Long
)

class SpellDto(
    @SerializedName("imageUrl") val imageUrl: String
)

class ItemDto(
    @SerializedName("imageUrl") val imageUrl: String
)

class StatsDto(
    @SerializedName("general") val general: GeneralDto,
    @SerializedName("ward") val ward: WardDto
)

class GeneralDto(
    @SerializedName("kill") val kill: Long,
    @SerializedName("death") val death: Long,
    @SerializedName("assist") val assist: Long,
    @SerializedName("kdaString") val kdaString: String,
    @SerializedName("cs") val cs: Long,
    @SerializedName("contributionForKillRate") val contributionForKillRate: String,
    @SerializedName("goldEarned") val goldEarned: Long,
    @SerializedName("totalDamageDealtToChampions") val totalDamageDealtToChampions: Long,
    @SerializedName("largestMultiKillString") val largestMultiKillString: String,
    @SerializedName("opScoreBadge") val opScoreBadge: String
)

class WardDto(
    @SerializedName("sightWardsBought") val sightWardsBought: Long,
    @SerializedName("visionWardsBought") val visionWardsBought: Long
)