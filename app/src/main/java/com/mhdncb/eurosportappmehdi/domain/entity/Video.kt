package com.mhdncb.eurosportappmehdi.domain.entity

data class Video(
    val date: Double = 0.0,
    val id: Int = 0,
    val sport: Sport = Sport(),
    val thumb: String = "",
    val title: String = "",
    val url: String = "",
    val views: Int = 0
)