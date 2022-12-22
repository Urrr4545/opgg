package com.urrr4545.opgg.domain.model

data class Position(
    val games: Long,
    val wins: Long,
    val losses: Long,
    val position: String,
    val positionName: String
)