package com.example.vpdtestapp.room.dao


import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "transactions")
data class Transaction(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val source: String,
    val destination: String,
    val amount: Double,
    val date: Long = System.currentTimeMillis() // Store the current timestamp
)
