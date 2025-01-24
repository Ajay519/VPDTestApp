package com.example.vpdtestapp.activity


import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.vpdtestapp.viewModel.AccountViewModel
import com.example.vpdtestapp.R
import com.example.vpdtestapp.databinding.ActivityTransferBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class TransferActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTransferBinding
    private val accountViewModel: AccountViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set up DataBinding
        binding = DataBindingUtil.setContentView(this, R.layout.activity_transfer)

        // Bind ViewModel to the layout
        binding.viewModel = accountViewModel
        binding.lifecycleOwner = this

        // Observe transfer summary to display transfer details
        accountViewModel.transferSummary.observe(this, Observer { summary ->
            binding.transferSummary.text = summary
        })

        // Observe transfer status (success or failure message)
        accountViewModel.transferStatus.observe(this, Observer { status ->
            Toast.makeText(this, status, Toast.LENGTH_SHORT).show()
        })

        // Set up source and destination account Spinners (to be filled with account numbers)
        accountViewModel.accounts.observe(this, Observer { accounts ->
            val accountNumbers = accounts.map { it.accountNumber }
            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, accountNumbers)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.sourceAccountSpinner.adapter = adapter
            binding.destinationAccountSpinner.adapter = adapter
        })

        // Set up Confirm Transfer button
        binding.confirmTransferButton.setOnClickListener {
            val sourceAccount = binding.sourceAccountSpinner.selectedItem.toString()
            val destinationAccount = binding.destinationAccountSpinner.selectedItem.toString()
            val transferAmount = binding.transferAmount.text.toString()

            // Set the values in the ViewModel
            accountViewModel.sourceAccount.value = sourceAccount
            accountViewModel.destinationAccount.value = destinationAccount
            accountViewModel.transferAmount.value = transferAmount

            // Perform the transfer
            GlobalScope.launch {
                accountViewModel.performTransfer()
            }
        }
    }
}

