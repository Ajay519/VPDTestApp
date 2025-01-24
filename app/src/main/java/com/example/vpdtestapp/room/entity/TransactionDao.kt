package com.example.vpdtestapp.room.entity



import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.vpdtestapp.room.dao.Transaction

@Dao
interface TransactionDao {

    // Insert a new transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insertTransaction(transaction: Transaction)

    // Get all transactions
    @Query("SELECT * FROM transactions")
    fun getAllTransactions(): LiveData<List<Transaction>>

    // Optionally, get transactions by account number (if needed)
    @Query("SELECT * FROM transactions WHERE source = :accountNumber OR destination = :accountNumber")
    fun getTransactionsByAccount(accountNumber: String): LiveData<List<Transaction>>
}

