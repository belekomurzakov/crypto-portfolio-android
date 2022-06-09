package com.omurzakov.cryptoportfolio.database

import androidx.lifecycle.LiveData
import com.omurzakov.cryptoportfolio.model.Crypto

interface ICryptoLocalRepository {
    fun getAll(): LiveData<MutableList<Crypto>>
    fun getAllPurchasedCrypto(): LiveData<MutableList<Crypto>>
    fun getAllPurchasedCryptoNoLive(): MutableList<Crypto>
    fun getSumAllPurchasedCrypto(): Float
    suspend fun findById(id: Long): Crypto
    fun insertCrypto(crypto: Crypto): Long
    suspend fun updateCrypto(crypto: Crypto)
    suspend fun deleteCrypto(crypto: Crypto)
}