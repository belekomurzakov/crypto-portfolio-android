package com.omurzakov.cryptoportfolio.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.omurzakov.cryptoportfolio.model.Crypto

@Dao
interface CryptoDao {

    @Query("SELECT * FROM crypto")
    fun getAll(): LiveData<MutableList<Crypto>>

    @Query("SELECT * FROM crypto WHERE purchasedAmount > 0.0")
    fun getAllPurchasedCrypto(): LiveData<MutableList<Crypto>>

    @Query("SELECT * FROM crypto WHERE purchasedAmount > 0.0")
    fun getAllPurchasedCryptoNoLive(): MutableList<Crypto>

    @Query("SELECT SUM(purchasedAmount) FROM crypto WHERE purchasedAmount > 0.0")
    fun getSumAllPurchasedCrypto(): Float

    @Query("SELECT * FROM crypto WHERE id = :id")
    suspend fun findById(id: Long): Crypto

    @Insert
    fun insertCrypto(crypto: Crypto): Long

    @Insert
    fun insertAll(cryptoList: List<Crypto>): List<Long>

    @Update
    suspend fun updateCrypto(crypto: Crypto)

    @Delete
    suspend fun deleteCrypto(crypto: Crypto)
}