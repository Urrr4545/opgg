package com.urrr4545.opgg.data.network.dto

import com.urrr4545.opgg.data.network.response.MatchResponse
import com.urrr4545.opgg.domain.model.*
import com.urrr4545.opgg.domain.utils.DomainMapper

class GameDtoMapper: DomainMapper<GameDto, Game> {

    fun toMatchData(initial: MatchResponse): Match {
        return Match(
            games = toGameList(initial.games),
            champion = toChampionList(initial.champion),
            positions = toPositionList(initial.positions)
        )
    }

    fun toDomainList(initial: List<GameDto>): List<Game>{
        return initial.map { mapToDomainModel(it) }
    }

    override fun mapToDomainModel(model: GameDto): Game {
        return model.toGame()
    }

    fun toGameList(games: List<GameDto>): List<Game>{
        return games.map { it.toGame() }
    }

    fun toChampionList(champions: List<ChampionDto>): List<Champion> {
        return champions.map { it.toChampion() }
    }

    fun toPositionList(positions: List<PositionDto>): List<Position> {
        return positions.map { it.toPosition() }
    }

    fun MatchDto.toMatch() =
        Match(
            games = toGameList(this.games),
            champion = toChampionList(this.champion),
            positions = toPositionList(this.positions)
        )

    fun PositionDto.toPosition() =
        Position(
            games = this.games,
            wins = this.wins,
            losses = this.losses,
            position = this.position,
            positionName = this.positionName
        )

    fun ChampionDto.toChampion() =
        Champion(
            id = this.id,
            key = this.key,
            name = this.name,
            imageUrl = this.imageUrl,
            games = this.games,
            kills = this.kills,
            deaths = this.deaths,
            assists = this.assists,
            wins = this.wins,
            losses = this.losses
        )

    fun GameDto.toGame() =
        Game(
            mmr = this.mmr,
            champion = this.champion.toGameChampion(),
            spells = this.spells.map {it.toSpell()},
            items = this.items.map {it.toItem()},
            needRenew = this.needRenew,
            gameId = this.gameId,
            createDate = this.createDate,
            gameLength = this.gameLength,
            gameType = this.gameType,
            summonerId = this.summonerId,
            summonerName = this.summonerName,
            tierRankShort = this.tierRankShort,
            stats = this.stats.toStats(),
            peak = this.peak.map {it},
            isWin = this.isWin
        )

    fun GameChampionDto.toGameChampion() =
        GameChampion(
            imageUrl = this.imageUrl,
            level = this.level
        )

    fun SpellDto.toSpell() =
        Spell(
            imageUrl = this.imageUrl
        )

    fun ItemDto.toItem() =
        Item(
            imageUrl = this.imageUrl
        )

    fun StatsDto.toStats() =
        Stats(
            general = this.general.toGeneral(),
            ward = this.ward.toWard()
        )

    fun GeneralDto.toGeneral() =
        General(
            kill = this.kill,
            death = this.death,
            assist = this.assist,
            kdaString = this.kdaString,
            cs = this.cs,
            contributionForKillRate = this.contributionForKillRate,
            goldEarned = this.goldEarned,
            totalDamageDealtToChampions = this.totalDamageDealtToChampions,
            largestMultiKillString = this.largestMultiKillString,
            opScoreBadge = this.opScoreBadge
        )

    fun WardDto.toWard() =
        Ward(
            sightWardsBought = this.sightWardsBought,
            visionWardsBought = this.visionWardsBought
        )
}