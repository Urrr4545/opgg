package com.urrr4545.opgg.domain.model

data class Champion(
    val id: Long,
    val key: String,
    val name: String,
    val imageUrl: String,
    val games: Long,
    val kills: Long,
    val deaths: Long,
    val assists: Long,
    val wins: Long,
    val losses: Long
)