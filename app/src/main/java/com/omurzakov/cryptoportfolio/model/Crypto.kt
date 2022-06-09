package com.omurzakov.cryptoportfolio.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "crypto")
class Crypto(
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "currencyCode") var currencyCode: String,
    @ColumnInfo(name = "image") var image: String,
    @ColumnInfo(name = "price") var price: Float
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long? = null

    @ColumnInfo(name = "purchasedAmount")
    var purchasedAmount: Float = 0.0F
}