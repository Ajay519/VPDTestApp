package com.example.vpdtestapp.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.vpdtestapp.room.dao.Transaction
import com.example.vpdtestapp.room.dao.UserAccount
import com.example.vpdtestapp.room.entity.AccountDao
import com.example.vpdtestapp.room.entity.TransactionDao

@Database(entities = [UserAccount::class, Transaction::class], version = 1, exportSchema = false)
abstract class AccountDatabase : RoomDatabase() {

    abstract fun accountDao(): AccountDao
    abstract fun transactionDao(): TransactionDao

    companion object {
        @Volatile
        private var INSTANCE: AccountDatabase? = null

        fun getDatabase(context: Context): AccountDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AccountDatabase::class.java,
                    "account_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
