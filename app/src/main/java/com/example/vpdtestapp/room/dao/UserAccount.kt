package com.example.vpdtestapp.room.dao

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_accounts")
data class UserAccount(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "account_number") val accountNumber: String,
    @ColumnInfo(name = "account_name") val accountName: String,
    var balance: Double
)
