package com.android.currencyconverterv2.core.util

import android.content.Context
import androidx.annotation.StringRes

sealed class UiText{
    data class DynamicString(val value: String): UiText()
    class StringResource(@StringRes val resId: Int, vararg var args: Any): UiText()


    fun asString(context: Context): String {
        return when(this){
            is UiText.DynamicString -> value
            is UiText.StringResource -> context.getString(resId, *args)
        }
    }
}
