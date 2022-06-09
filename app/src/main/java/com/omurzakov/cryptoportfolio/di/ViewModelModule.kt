package com.omurzakov.cryptoportfolio.di

import com.omurzakov.cryptoportfolio.viewmodels.*
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        AddCryptoViewModel(get(), get())
    }
    viewModel {
        RemoveCryptoViewModel(get(), get())
    }
    viewModel {
        PriceListViewModel(get())
    }
    viewModel {
        HistoryListViewModel(get())
    }
    viewModel {
        PortfolioValueViewModel(get())
    }
    viewModel {
        AnalyticsViewModel(get())
    }
}