package com.omurzakov.cryptoportfolio.di

import android.content.Context
import com.omurzakov.cryptoportfolio.database.CryptoDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    single {
        provideDatabase(androidContext())
    }
}

fun provideDatabase(context: Context): CryptoDatabase =
    CryptoDatabase.getDatabase(context)