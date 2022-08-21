package com.android.currencyconverterv2.data.remote.dto

import com.android.currencyconverterv2.domain.model.NotNullConvert

class NotNullApiDto

data class NotNullDto(
    val name: String,
    val symbol: String
)

data class NotNullConvertDto(
    val result: ResultDto,
    val success: Boolean,
    val validationMessage: List<Any>
){
    fun toNotNullConvert(): NotNullConvert {
        return NotNullConvert(result = result)
    }
}

data class ResultDto(
    val amountToConvert: Any = "",
    val convertedAmount: Any = "",
    val from: String = "",
    val to: String = ""
){

//    fun toResult(): Result {
//        return Result(
//            amountToConvert = amountToConvert,
//            convertedAmount = convertedAmount,
//            from = from,
//            to = to
//        )
//    }

}