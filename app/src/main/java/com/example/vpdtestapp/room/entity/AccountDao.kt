package com.example.vpdtestapp.room.entity

import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import androidx.room.Dao
import androidx.room.Delete
import com.example.vpdtestapp.room.dao.UserAccount

@Dao
interface AccountDao {

    // Insert a new account
    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insertAccount(account: UserAccount)

    // Get all accounts
    @Query("SELECT * FROM user_accounts")
    fun getAllAccounts(): LiveData<List<UserAccount>>

    // Get account by account number
    @Query("SELECT * FROM user_accounts WHERE account_number = :accountNumber LIMIT 1")
    fun getAccountByNumber(accountNumber: String): UserAccount?

    // Update an existing account
    @Update
     fun updateAccount(account: UserAccount)

    // Delete an account (if needed)
    @Delete
     fun deleteAccount(account: UserAccount)
}
