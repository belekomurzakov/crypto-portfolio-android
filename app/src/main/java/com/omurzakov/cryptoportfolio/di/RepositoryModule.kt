package com.omurzakov.cryptoportfolio.di

import com.omurzakov.cryptoportfolio.database.*
import org.koin.dsl.module

val repositoryModule = module {
    single {
        provideLocalCryptoRepository(get())
    }
    single {
        provideLocalPurchaseHistoryRepository(get())
    }
}

fun provideLocalCryptoRepository(dao: CryptoDao): ICryptoLocalRepository =
    CryptoLocalRepositoryImpl(dao)

fun provideLocalPurchaseHistoryRepository(dao: PurchaseHistoryDao): IPurchaseHistoryLocalRepository =
    PurchaseHistoryLocalRepositoryImpl(dao)