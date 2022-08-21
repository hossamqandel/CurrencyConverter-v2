package com.android.currencyconverterv2.domain.repository

import com.android.currencyconverterv2.data.remote.dto.NotNullConvertDto

interface INotNullRepository {

    suspend fun notNullConvert(from: String, to: String, amount: Int): NotNullConvertDto
}