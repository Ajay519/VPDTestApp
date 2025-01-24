package com.example.vpdtestapp.activity


import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vpdtestapp.adapter.AccountAdapter
import com.example.vpdtestapp.viewModel.AccountViewModel
import com.example.vpdtestapp.R
import com.example.vpdtestapp.room.dao.UserAccount
import com.example.vpdtestapp.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var recyclerView: RecyclerView
    private val accountViewModel: AccountViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set up DataBinding
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // Bind ViewModel to the layout
        binding.viewModel = accountViewModel
        binding.lifecycleOwner = this

        // Set up RecyclerView with an adapter
        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Observe accounts LiveData and update the RecyclerView
        accountViewModel.accounts.observe(this, Observer { accounts ->
            val accountAdapter = AccountAdapter(accounts) { account ->
                // Handle account click - open TransferActivity
                openTransferActivity(account)
            }
            recyclerView.adapter = accountAdapter
        })
    }

    // Open TransferActivity with selected account info
    private fun openTransferActivity(account: UserAccount) {
        val intent = Intent(this, TransferActivity::class.java)
        intent.putExtra("account_number", account.accountNumber)  // Pass account info
        intent.putExtra("account_balance", account.balance)  // Optional: Pass balance
        startActivity(intent)
    }
}
