package com.example.vpdtestapp.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.vpdtestapp.AccountRepository
import com.example.vpdtestapp.room.dao.Transaction
import com.example.vpdtestapp.room.dao.UserAccount

class AccountViewModel(application: Application) : AndroidViewModel(application) {

    private val accountRepository: AccountRepository = AccountRepository(application)
    val accounts: LiveData<List<UserAccount>> = accountRepository.getAllAccounts()
    val transactions: LiveData<List<Transaction>> = accountRepository.getAllTransactions()

    // MutableLiveData to store user input for the transfer
    val sourceAccount = MutableLiveData<String>()
    val destinationAccount = MutableLiveData<String>()
    val transferAmount = MutableLiveData<String>()
    val transferStatus = MutableLiveData<String>()
    val transferSummary = MutableLiveData<String>()

    // Method to simulate account transfer
    fun performTransfer() {

        val source = sourceAccount.value.orEmpty()
        val destination = destinationAccount.value.orEmpty()
        val amount = transferAmount.value?.toDoubleOrNull() ?: 0.0

        // Validate input
        Log.e("a",source.isBlank().toString())
        Log.e("b",destination.isBlank().toString())
        Log.e("c",amount.toString())
        if (source.isBlank() || destination.isBlank() || amount <= 0) {
            transferStatus.value = "Invalid input"
            return
        }

        // Get source and destination accounts from the repository
        val sourceAccountData = accountRepository.getAccountByNumber(source)
        val destinationAccountData = accountRepository.getAccountByNumber(destination)

        if (sourceAccountData == null || destinationAccountData == null) {
            transferStatus.value = "Source or destination account not found"
            return
        }

        // Validate sufficient funds in the source account
        if (sourceAccountData.balance < amount) {
            transferStatus.value = "Insufficient funds"
            return
        }

        // Perform the transfer: update balances
        sourceAccountData.balance -= amount
        destinationAccountData.balance += amount

        // Save the updated accounts and the transaction
        accountRepository.updateAccount(sourceAccountData)
        accountRepository.updateAccount(destinationAccountData)

        // Create a transaction record
        val transaction = Transaction(
            source = sourceAccountData.accountNumber,
            destination = destinationAccountData.accountNumber,
            amount = amount
        )
        saveTransaction(transaction)

        // Update transfer summary and status
        transferSummary.value = "Transfer Summary:\nSource: ${sourceAccountData.accountNumber}\nDestination: ${destinationAccountData.accountNumber}\nAmount: $amount"
        transferStatus.value = "Transfer successful"
    }

    // Method to save a transaction to the Room database
    fun saveTransaction(transaction: Transaction) {
        accountRepository.saveTransaction(transaction)
    }
}


