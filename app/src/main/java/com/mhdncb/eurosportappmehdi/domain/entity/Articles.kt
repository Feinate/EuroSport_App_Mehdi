package com.mhdncb.eurosportappmehdi.domain.entity

data class Articles(
    val stories: List<Story> = listOf(),
    val videos: List<Video> = listOf()
)