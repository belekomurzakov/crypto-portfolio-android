package com.omurzakov.cryptoportfolio.viewmodels

import androidx.lifecycle.ViewModel
import com.omurzakov.cryptoportfolio.database.ICryptoLocalRepository
import com.omurzakov.cryptoportfolio.database.IPurchaseHistoryLocalRepository
import com.omurzakov.cryptoportfolio.model.Crypto
import com.omurzakov.cryptoportfolio.model.PurchaseHistory

class AddCryptoViewModel(
    private val cryptoRepository: ICryptoLocalRepository,
    private val purchaseHistoryRepository: IPurchaseHistoryLocalRepository
) : ViewModel() {

    var cryptoId: Long = -1L

    var crypto: Crypto = Crypto("", "", "", 0.0F)
    var purchaseHistory = PurchaseHistory(cryptoId, true, 0.0F, 0.0F, "")

    suspend fun savePurchaseHistory() {
        purchaseHistoryRepository.insertPurchaseHistory(purchaseHistory)
    }

    suspend fun updateCrypto() {
        if (cryptoId != -1L) cryptoRepository.updateCrypto(crypto)
    }

    suspend fun findCryptoById(): Crypto = cryptoRepository.findById(cryptoId)
}