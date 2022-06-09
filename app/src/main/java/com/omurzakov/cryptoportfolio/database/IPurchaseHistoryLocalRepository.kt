package com.omurzakov.cryptoportfolio.database

import androidx.lifecycle.LiveData
import com.omurzakov.cryptoportfolio.model.Crypto
import com.omurzakov.cryptoportfolio.model.PurchaseHistory

interface IPurchaseHistoryLocalRepository {
    fun getAllPurchaseHistory() : LiveData<MutableList<PurchaseHistory>>
    suspend fun insertPurchaseHistory(purchaseHistory: PurchaseHistory): Long
    suspend fun deleteHistory(purchaseHistory: PurchaseHistory)
    suspend fun findById(id: Long): PurchaseHistory
}