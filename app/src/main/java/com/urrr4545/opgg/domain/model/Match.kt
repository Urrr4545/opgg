package com.urrr4545.opgg.domain.model

data class Match(
    val games: List<Game>,
    val champion: List<Champion>,
    val positions: List<Position>
)