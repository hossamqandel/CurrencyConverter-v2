package com.android.currencyconverterv2.data.remote

import com.android.currencyconverterv2.data.remote.dto.NotNullConvertDto
import retrofit2.http.GET
import retrofit2.http.Query

interface IWebService {

    @GET("convert")
    suspend fun notNullConvert(
        @Query("from")from: String,
        @Query("to")to: String,
        @Query("amount")amount: Any = ""
    ): NotNullConvertDto
}