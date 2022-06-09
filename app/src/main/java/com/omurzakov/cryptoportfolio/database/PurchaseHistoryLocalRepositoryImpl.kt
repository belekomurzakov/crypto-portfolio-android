package com.omurzakov.cryptoportfolio.database

import androidx.lifecycle.LiveData
import com.omurzakov.cryptoportfolio.model.PurchaseHistory

class PurchaseHistoryLocalRepositoryImpl(private val purchaseHistoryDao: PurchaseHistoryDao) :
    IPurchaseHistoryLocalRepository {

    override fun getAllPurchaseHistory(): LiveData<MutableList<PurchaseHistory>> {
        return purchaseHistoryDao.getAllPurchaseHistory()
    }

    override suspend fun insertPurchaseHistory(purchaseHistory: PurchaseHistory): Long {
        return purchaseHistoryDao.insertPurchaseHistory(purchaseHistory)
    }

    override suspend fun deleteHistory(purchaseHistory: PurchaseHistory) =
        purchaseHistoryDao.deleteHistory(purchaseHistory)

    override suspend fun findById(id: Long): PurchaseHistory {
        return purchaseHistoryDao.findById(id)
    }
}