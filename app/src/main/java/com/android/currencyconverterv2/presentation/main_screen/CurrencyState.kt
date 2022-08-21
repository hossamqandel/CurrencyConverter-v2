package com.android.currencyconverterv2.presentation.main_screen

import com.android.currencyconverterv2.data.remote.dto.ResultDto

data class CurrencyState(
    val isLoading: Boolean = false,
    val resultDto: ResultDto? = ResultDto(),
    val error: String = ""
)
