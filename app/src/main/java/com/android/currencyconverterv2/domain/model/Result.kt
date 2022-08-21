package com.android.currencyconverterv2.domain.model

data class Result(
    val amountToConvert: Any? = null,
    val convertedAmount: Double? = null,
    val from: String = "",
    val to: String = ""
)
