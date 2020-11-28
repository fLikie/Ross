package com.example.rosseti.application

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.math.BigDecimal

@Parcelize
data class Spending(
    val index: Int,
    val name: String,
    val sum: Long,
) : Parcelable
