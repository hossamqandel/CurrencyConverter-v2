package com.android.currencyconverterv2.domain.use_case

import android.util.Log
import androidx.core.text.isDigitsOnly
import com.android.currencyconverterv2.core.util.Resource
import com.android.currencyconverterv2.domain.model.NotNullConvert
import com.android.currencyconverterv2.domain.repository.INotNullRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class CurrencyConvertUseCase @Inject constructor(
    private val iNotNullRepository: INotNullRepository
) {

    private val TAG = "NotNullConvertUseCase"

    operator fun invoke(
        from: String,
        to: String,
        amount: String
    ): Flow<Resource<NotNullConvert>> = flow {

        emit(Resource.Loading())

        try {
            if (amount.isBlank()) {
                emit(Resource.Error("Please enter an amount"))
                return@flow
            }

            if (amount.isNotBlank()) {
                if (amount.isDigitsOnly()) {
                    if (amount.toInt() <= 0) {
                        emit(Resource.Error("Enter a value bigger than zero"))
                        return@flow
                    }
                }
            }

            val data =
                iNotNullRepository.notNullConvert(from, to, amount.toInt()).toNotNullConvert()
            emit(Resource.Success(data))

        } catch (e: HttpException) {
//            emit(Resource.Error("Enter a value bigger than zero"))
            Log.e(TAG, "invoke: ${e.message}")

        } catch (e: Exception) {
//            emit(Resource.Error("Enter a value bigger than zero"))
            Log.e(TAG, "invoke: ${e.message}")

        } catch (e: IOException) {
            emit(Resource.Error("Please check your internet connection"))
            Log.e(TAG, "invoke: ${e.message}")
        }
    }

}