package com.omurzakov.cryptoportfolio.di

import com.omurzakov.cryptoportfolio.database.CryptoDao
import com.omurzakov.cryptoportfolio.database.CryptoDatabase
import com.omurzakov.cryptoportfolio.database.PurchaseHistoryDao
import org.koin.dsl.module

val daoModule = module {
    single {
        provideCryptoDao(get())
    }
    single {
        providePurchaseHistoryDao(get())
    }
}

fun provideCryptoDao(database: CryptoDatabase): CryptoDao =
    database.cryptoDao()

fun providePurchaseHistoryDao(database: CryptoDatabase): PurchaseHistoryDao =
    database.purchaseHistoryDao()