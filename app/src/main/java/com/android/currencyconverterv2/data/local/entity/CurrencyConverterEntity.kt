package com.android.currencyconverterv2.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CurrencyConverterEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val date: String,
//    val info: InfoDto,
//    val query: QueryDto,
    val result: Double,
    val success: Boolean
)
