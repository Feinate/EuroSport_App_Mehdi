package com.mhdncb.eurosportappmehdi.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Sport(
    val id: Int = 0,
    val name: String = ""
) : Parcelable