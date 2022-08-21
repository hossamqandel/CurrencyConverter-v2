package com.android.currencyconverterv2.data.repository

import com.android.currencyconverterv2.data.remote.IWebService
import com.android.currencyconverterv2.data.remote.dto.NotNullConvertDto
import com.android.currencyconverterv2.domain.repository.INotNullRepository
import javax.inject.Inject

class NotNullRepositoryImpl @Inject constructor(
    private val iWebService: IWebService
) : INotNullRepository {

    override suspend fun notNullConvert(from: String, to: String, amount: Int): NotNullConvertDto {
        return iWebService.notNullConvert(from, to, amount)
    }
}