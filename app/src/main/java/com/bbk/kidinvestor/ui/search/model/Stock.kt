package com.bbk.kidinvestor.ui.search.model

import java.util.*

data class Stock(
    val name: String,
    val symbol: String,
    val currency: String,
    val exchange: String,
    val country: String,
    val type: String,
    val open: Double,
    val high: Double,
    val low: Double,
    val close: Double,
    val change: Double,
    val volume: Int,
    val date: Date,
)
