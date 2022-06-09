package com.omurzakov.cryptoportfolio.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.omurzakov.cryptoportfolio.model.Crypto
import com.omurzakov.cryptoportfolio.model.PurchaseHistory
import com.omurzakov.cryptoportfolio.utilities.ioThread

@Database(entities = [Crypto::class, PurchaseHistory::class], version = 2, exportSchema = true)
abstract class CryptoDatabase : RoomDatabase() {
    abstract fun cryptoDao(): CryptoDao
    abstract fun purchaseHistoryDao(): PurchaseHistoryDao

    companion object {
        @Volatile
        private var INSTANCE: CryptoDatabase? = null
        private var PREPOPULATE_DATA: List<Crypto> = mutableListOf(
            Crypto("Bitcoin", "BTC", "@drawable/btc", 42537.10F),
            Crypto("Bibox", "BIX", "@drawable/bix", 3149.13F),
            Crypto("Ethereum", "ETH", "@drawable/eth", 537.10F),
            Crypto("Ardor", "ARDR", "@drawable/ardr", 0.21F),
            Crypto("Electroneum", "ETN", "@drawable/etn", 0.01F),
            Crypto("Fantom", "FTM", "@drawable/ftm", 12000.0F),
            Crypto("Insight Chain", "INB", "@drawable/inb", 213.0F),
            Crypto("Smooth Love Potion", "SLP", "@drawable/slp", 1000.0F),
            Crypto("TKN", "TKN", "@drawable/tkn", 0.41F),
            Crypto("VelocityCoin", "VELL", "@drawable/veil", 322.0F),
            Crypto("Bitcoin", "BTC", "@drawable/btc", 42537.10F),
            Crypto("Bibox", "BIX", "@drawable/bix", 3149.13F),
            Crypto("Ethereum", "ETH", "@drawable/eth", 537.10F),
            Crypto("Ardor", "ARDR", "@drawable/ardr", 0.21F),
            Crypto("Electroneum", "ETN", "@drawable/etn", 0.01F),
            Crypto("Fantom", "FTM", "@drawable/ftm", 12000.0F),
            Crypto("Insight Chain", "INB", "@drawable/inb", 213.0F),
            Crypto("Smooth Love Potion", "SLP", "@drawable/slp", 1000.0F),
            Crypto("TKN", "TKN", "@drawable/tkn", 0.41F),
            Crypto("VelocityCoin", "VELL", "@drawable/veil", 322.0F)
        )

        fun getDatabase(context: Context): CryptoDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                CryptoDatabase::class.java,
                "crypto_database"
            )
                .allowMainThreadQueries()
                .addCallback(object : Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        ioThread {
                            getDatabase(context).cryptoDao().insertAll(PREPOPULATE_DATA)
                        }
                    }
                })
                .build()
    }
}