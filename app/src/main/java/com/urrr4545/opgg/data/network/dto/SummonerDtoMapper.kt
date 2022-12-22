package com.urrr4545.opgg.data.network.dto

import com.urrr4545.opgg.domain.model.League
import com.urrr4545.opgg.domain.model.Summoner
import com.urrr4545.opgg.domain.model.TierRank
import com.urrr4545.opgg.domain.utils.DomainMapper

class SummonerDtoMapper: DomainMapper<SummonerDto, Summoner> {

    override fun mapToDomainModel(model: SummonerDto): Summoner {
        return model.toSummoner()
    }

    fun SummonerDto.toSummoner() =
        Summoner(
            name = this.name,
            level = this.level,
            profileImageUrl = this.profileImageUrl,
            leagues = this.leagues.map {
                it.toLeagueList()
            }
        )

    fun LeagueDto.toLeagueList() =
        League(
            wins = this.wins,
            losses = this.losses,
            tierRank = this.tierRank.toTierRank()
        )

    fun TierRankDto.toTierRank() =
        TierRank(
            name = this.name,
            imageUrl = this.imageUrl,
            tier = this.tier,
            lp = this.lp,
            tierRankPoint = this.tierRankPoint
        )
}