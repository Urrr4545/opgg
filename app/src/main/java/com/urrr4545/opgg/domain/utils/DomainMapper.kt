package com.urrr4545.opgg.domain.utils

interface DomainMapper <T, DomainModel>{

    fun mapToDomainModel(model: T): DomainModel

}