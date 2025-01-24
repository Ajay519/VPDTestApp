package com.example.vpdtestapp

import android.app.Application
import com.example.vpdtestapp.room.AccountDatabase
import com.example.vpdtestapp.room.dao.UserAccount
import com.example.vpdtestapp.room.entity.AccountDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        // Pre-populate the database if needed
        val database = AccountDatabase.getDatabase(applicationContext)
        val accountDao = database.accountDao()

        // Check SharedPreferences to ensure we populate the database only once
        val sharedPreferences = getSharedPreferences("app_preferences", MODE_PRIVATE)
        val isDatabasePopulated = sharedPreferences.getBoolean("is_database_populated", false)

        if (!isDatabasePopulated) {
            populateDatabase(accountDao)
            sharedPreferences.edit().putBoolean("is_database_populated", true).apply()
        }
    }

    fun populateDatabase(accountDao: AccountDao) {
        val account1 = UserAccount(accountNumber = "123456789", accountName = "User1", balance = 10000.0)
        val account2 = UserAccount(accountNumber = "987654321", accountName = "User2", balance = 5000.0)
        val account3 = UserAccount(accountNumber = "587654321", accountName = "User3", balance = 4000.0)
        val account4 = UserAccount(accountNumber = "983354321", accountName = "User4", balance = 12000.0)
        val account5 = UserAccount(accountNumber = "777654321", accountName = "User5", balance = 7000.0)

        CoroutineScope(Dispatchers.IO).launch {
            accountDao.insertAccount(account1)
            accountDao.insertAccount(account2)
            accountDao.insertAccount(account3)
            accountDao.insertAccount(account4)
            accountDao.insertAccount(account5)
        }
    }}
