package com.omurzakov.cryptoportfolio.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.omurzakov.cryptoportfolio.database.IPurchaseHistoryLocalRepository
import com.omurzakov.cryptoportfolio.model.Crypto
import com.omurzakov.cryptoportfolio.model.PurchaseHistory

class HistoryListViewModel(private val repository: IPurchaseHistoryLocalRepository) : ViewModel() {

    var purchaseHistory: PurchaseHistory =
        PurchaseHistory(0L, false, 0.0F, 0.0F, "")


    fun getAll(): LiveData<MutableList<PurchaseHistory>> = repository.getAllPurchaseHistory()

    suspend fun deleteHistory(purchaseHistory: PurchaseHistory) = repository.deleteHistory(purchaseHistory)

    suspend fun findPurchaseHistoryById(id: Long): PurchaseHistory = repository.findById(id)
}