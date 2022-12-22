package com.urrr4545.opgg.domain.model

data class AnalysisData(
    val winLossStr: String,
    val avgKills: String,
    val avgDeaths: String,
    val avgAssists: String,
    val avgKda: String,
    val avgWinPer: String,
    val mostChamp1: MostChamp?,
    val mostChamp2: MostChamp?,
    val mostPos: String,
    val mostPosPer: String
)

data class MostChamp(
    val imgUrl: String,
    val winPer: String
)