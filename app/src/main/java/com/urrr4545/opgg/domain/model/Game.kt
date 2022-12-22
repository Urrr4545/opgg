package com.urrr4545.opgg.domain.model

data class Game(
    val mmr: Long,
    val champion: GameChampion,
    val spells: List<Spell>,
    val items: List<Item>,
    val needRenew: Boolean,
    val gameId: String,
    val createDate: Long?,
    val gameLength: Long,
    val gameType: String,
    val summonerId: String,
    val summonerName: String,
    val tierRankShort: String,
    val stats: Stats,
    val peak: List<String>,
    val isWin: Boolean
)

data class GameChampion(
    val imageUrl: String,
    val level: Long
)

data class Spell(
    val imageUrl: String
)

data class Item(
    val imageUrl: String
)

data class Stats(
    val general: General,
    val ward: Ward
)

data class General(
    val kill: Long,
    val death: Long,
    val assist: Long,
    val kdaString: String,
    val cs: Long,
    val contributionForKillRate: String,
    val goldEarned: Long,
    val totalDamageDealtToChampions: Long,
    val largestMultiKillString: String,
    val opScoreBadge: String
)

data class Ward(
    val sightWardsBought: Long,
    val visionWardsBought: Long
)