package com.urrr4545.opgg.domain.utils

import android.util.Log
import com.urrr4545.opgg.domain.model.*

fun Match.toAnalysis(): AnalysisData {

    var wins = 0
    var losses = 0
    var kills = 0
    var deaths = 0
    var assists = 0

    val games = this.games
    val gameSize = games.size
    games.forEach {
        if(it.isWin) wins += 1 else losses += 1
        kills += it.stats.general.kill.toInt()
        deaths += it.stats.general.death.toInt()
        assists += it.stats.general.assist.toInt()
    }

    val champItems = this.champion.sortedBy { it.toWinRate() }
    var most1 : MostChamp? = null
    var most2 : MostChamp? = null
    champItems.forEachIndexed { index, champion ->
        if(index == 0) {
            most1 = champion.toMostChamp()
        } else if(index == 1) {
            most2 = champion.toMostChamp()
        }
    }

    val mostPos = this.positions.maxBy { it.toWinRate() }

    Log.e("vv"," kills : ${kills}")
    Log.e("vv"," deaths : ${deaths}")
    Log.e("vv"," assists : ${assists}")
    return AnalysisData(
        winLossStr = getwinLossStr(wins, losses),
        avgKills = (kills / gameSize).toDouble().toString(),
        avgDeaths = (deaths / gameSize).toDouble().toString(),
        avgAssists = (assists / gameSize).toDouble().toString(),
        avgKda =  getKda(kills, deaths, assists),
        avgWinPer = getWinPerStr(wins, losses),
        mostChamp1 = most1,
        mostChamp2 = most2,
        mostPos = mostPos.position,
        mostPosPer = getWinPerStr(mostPos.wins.toInt(), mostPos.losses.toInt())
    )
}

fun Position.toWinRate(): Int {
    return getWinPer(wins.toInt(), losses.toInt())
}

fun Champion.toMostChamp(): MostChamp {
    return MostChamp(
        imgUrl = imageUrl,
        winPer = getWinPerStr(wins.toInt(), losses.toInt())
    )
}

fun Champion.toWinRate(): Int {
    return getWinPer(this.wins.toInt(), this.losses.toInt())
}

fun getwinLossStr(wins: Int, losses: Int): String =
    "${wins}승 ${losses}패"

fun getKda(kills: Int, deaths: Int, assists: Int): String =
    "${((kills + assists) / deaths).toDouble()}: 1"

fun getWinPer(wins: Int, losses: Int): Int =
    (wins * 100 / (wins + losses))

fun getWinPerStr(wins: Int, losses: Int): String =
    "${(wins * 100 / (wins + losses))}%"