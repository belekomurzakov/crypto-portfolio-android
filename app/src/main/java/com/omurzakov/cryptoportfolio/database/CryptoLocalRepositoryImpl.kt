package com.omurzakov.cryptoportfolio.database

import androidx.lifecycle.LiveData
import com.omurzakov.cryptoportfolio.model.Crypto

class CryptoLocalRepositoryImpl(private val cryptoDao: CryptoDao) : ICryptoLocalRepository {
    override fun getAll(): LiveData<MutableList<Crypto>> {
        return cryptoDao.getAll()
    }

    override fun getAllPurchasedCrypto(): LiveData<MutableList<Crypto>> {
        return cryptoDao.getAllPurchasedCrypto()
    }

    override fun getAllPurchasedCryptoNoLive(): MutableList<Crypto> {
        return cryptoDao.getAllPurchasedCryptoNoLive()
    }

    override fun getSumAllPurchasedCrypto(): Float {
        return cryptoDao.getSumAllPurchasedCrypto()
    }

    override suspend fun findById(id: Long): Crypto {
        return cryptoDao.findById(id)
    }

    override fun insertCrypto(crypto: Crypto): Long {
        return cryptoDao.insertCrypto(crypto)
    }

    override suspend fun updateCrypto(crypto: Crypto) {
        return cryptoDao.updateCrypto(crypto)
    }

    override suspend fun deleteCrypto(crypto: Crypto) {
        return cryptoDao.deleteCrypto(crypto)
    }
}