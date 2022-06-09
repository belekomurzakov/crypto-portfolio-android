package com.omurzakov.cryptoportfolio.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.omurzakov.cryptoportfolio.database.ICryptoLocalRepository
import com.omurzakov.cryptoportfolio.database.IPurchaseHistoryLocalRepository
import com.omurzakov.cryptoportfolio.model.Crypto
import com.omurzakov.cryptoportfolio.model.PurchaseHistory

class PortfolioValueViewModel(private val repository: ICryptoLocalRepository) :
    ViewModel() {

    fun getAllPurchasedCrypto(): LiveData<MutableList<Crypto>> = repository.getAllPurchasedCrypto()

    fun getSumAllPurchasedCrypto(): Float = repository.getSumAllPurchasedCrypto()

}