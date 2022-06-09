package com.omurzakov.cryptoportfolio

import android.app.Application
import com.omurzakov.cryptoportfolio.di.daoModule
import com.omurzakov.cryptoportfolio.di.databaseModule
import com.omurzakov.cryptoportfolio.di.repositoryModule
import com.omurzakov.cryptoportfolio.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class CryptoPortfolioApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
            modules(
                databaseModule,
                daoModule,
                repositoryModule,
                viewModelModule
            )
        }
    }
}