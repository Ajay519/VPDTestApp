package com.example.vpdtestapp.activity



import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.vpdtestapp.viewModel.LoginViewModel
import com.example.vpdtestapp.R
import com.example.vpdtestapp.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize DataBinding
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        // Bind ViewModel to the layout
        binding.viewModel = loginViewModel
        binding.lifecycleOwner = this

        // Observe login status
        loginViewModel.loginStatus.observe(this) { status ->
            if (status.startsWith("Welcome")) {
                // Navigate to MainActivity after successful login or sign up
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                // Show login or sign up error
                Toast.makeText(this, status, Toast.LENGTH_SHORT).show()
            }
        }
    }
}
