package com.mhdncb.eurosportappmehdi.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Story(
    val author: String = "",
    val date: Double = 0.0,
    val id: Int = 0,
    val image: String = "",
    val sport: Sport = Sport(),
    val teaser: String = "",
    val title: String = ""
) : Parcelable