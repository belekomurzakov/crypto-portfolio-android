package com.omurzakov.cryptoportfolio.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "purchaseHistory")
class PurchaseHistory(
    @ColumnInfo(name = "cryptoId") var cryptoId: Long,
    @ColumnInfo(name = "isPurchased") var isPurchased: Boolean,
    @ColumnInfo(name = "amount") var amount: Float,
    @ColumnInfo(name = "quantity") var quantity: Float,
    @ColumnInfo(name = "date") var date: String
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long? = null

    @ColumnInfo(name = "cryptoName")
    var cryptoName: String? = null

    @ColumnInfo(name = "cryptoCurrencyCode")
    var cryptoCurrencyCode: String? = null

    @ColumnInfo(name = "cryptoImage")
    var cryptoImage: String? = null

    @ColumnInfo(name = "cryptoPrice")
    var cryptoPrice: Float? = null
}