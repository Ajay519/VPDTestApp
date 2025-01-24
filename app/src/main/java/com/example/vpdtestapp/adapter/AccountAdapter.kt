package com.example.vpdtestapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.vpdtestapp.databinding.AccountItemBinding
import com.example.vpdtestapp.room.dao.UserAccount


class AccountAdapter(private val accounts: List<UserAccount>, private val onItemClick: (UserAccount) -> Unit) : RecyclerView.Adapter<AccountAdapter.AccountViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccountViewHolder {
        val binding = AccountItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AccountViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AccountViewHolder, position: Int) {
        val account = accounts[position]
        holder.binding.account = account

        // Set click listener to open TransferActivity
        holder.itemView.setOnClickListener {
            onItemClick(account) // This will trigger the item click event
        }
    }

    override fun getItemCount(): Int = accounts.size

    class AccountViewHolder(val binding: AccountItemBinding) : RecyclerView.ViewHolder(binding.root)
}
