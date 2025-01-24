package com.example.vpdtestapp



import android.app.Application
import androidx.lifecycle.LiveData
import com.example.vpdtestapp.room.AccountDatabase
import com.example.vpdtestapp.room.dao.Transaction
import com.example.vpdtestapp.room.dao.UserAccount
import com.example.vpdtestapp.room.entity.AccountDao
import com.example.vpdtestapp.room.entity.TransactionDao


class AccountRepository(application: Application) {

    private val accountDao: AccountDao = AccountDatabase.getDatabase(application).accountDao()
    private val transactionDao: TransactionDao = AccountDatabase.getDatabase(application).transactionDao()

    // Get all accounts
    fun getAllAccounts(): LiveData<List<UserAccount>> {
        return accountDao.getAllAccounts()
    }

    // Get account by account number
    fun getAccountByNumber(accountNumber: String): UserAccount? {
        return accountDao.getAccountByNumber(accountNumber)
    }

    // Perform a transfer (update account balances)
    fun updateAccount(account: UserAccount) {
        accountDao.updateAccount(account)
    }

    // Save a transaction
    fun saveTransaction(transaction: Transaction) {
        transactionDao.insertTransaction(transaction)
    }

    // Get all transactions
    fun getAllTransactions(): LiveData<List<Transaction>> {
        return transactionDao.getAllTransactions()
    }


}

