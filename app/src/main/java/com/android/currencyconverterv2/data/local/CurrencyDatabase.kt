package com.android.currencyconverterv2.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.android.currencyconverterv2.data.local.entity.CurrencyConverterEntity

@Database(entities = [CurrencyConverterEntity::class], version = 1, exportSchema = false)
abstract class CurrencyDatabase : RoomDatabase(){

    abstract val dao: CurrencyDao

    companion object {
        private const val DATABASE_NAME = "currency_db"
        fun databaseName(): String = DATABASE_NAME
    }
}