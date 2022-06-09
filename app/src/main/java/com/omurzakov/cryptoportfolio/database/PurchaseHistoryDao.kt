package com.omurzakov.cryptoportfolio.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.omurzakov.cryptoportfolio.model.Crypto
import com.omurzakov.cryptoportfolio.model.PurchaseHistory

@Dao
interface PurchaseHistoryDao {

    @Query("SELECT * FROM purchaseHistory")
    fun getAllPurchaseHistory(): LiveData<MutableList<PurchaseHistory>>

    @Insert
    suspend fun insertPurchaseHistory(purchaseHistory: PurchaseHistory): Long

    @Delete
    suspend fun deleteHistory(purchaseHistory: PurchaseHistory)

    @Query("SELECT * FROM purchaseHistory WHERE id = :id")
    suspend fun findById(id: Long): PurchaseHistory
}