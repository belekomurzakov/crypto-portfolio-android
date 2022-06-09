package com.omurzakov.cryptoportfolio.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.omurzakov.cryptoportfolio.database.ICryptoLocalRepository
import com.omurzakov.cryptoportfolio.model.Crypto

class AnalyticsViewModel(private val repository: ICryptoLocalRepository) : ViewModel() {
    fun getAllPurchasedCrypto(): LiveData<MutableList<Crypto>> = repository.getAllPurchasedCrypto()
    fun getAllPurchasedCryptoNoLive(): MutableList<Crypto> = repository.getAllPurchasedCryptoNoLive()
    fun getSumAllPurchasedCrypto(): Float = repository.getSumAllPurchasedCrypto()
}