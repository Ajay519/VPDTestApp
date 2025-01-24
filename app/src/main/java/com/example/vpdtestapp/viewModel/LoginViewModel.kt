package com.example.vpdtestapp.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import android.util.Log

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    private val _loginStatus = MutableLiveData<String>()
    val loginStatus: LiveData<String> get() = _loginStatus

    // MutableLiveData for email and password to bind with the UI
    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()

    // Function to login user using Firebase Authentication
    fun login() {
        val emailText = email.value.orEmpty()
        val passwordText = password.value.orEmpty()

        if (emailText.isEmpty() || passwordText.isEmpty()) {
            _loginStatus.value = "Please enter email and password"
            return
        }

        auth.signInWithEmailAndPassword(emailText, passwordText)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user: FirebaseUser? = auth.currentUser
                    _loginStatus.value = "Welcome, ${user?.email}"
                    // Navigate to main activity
                } else {
                    _loginStatus.value = "Login failed: ${task.exception?.message}"
                    Log.e("Login", "Login failed", task.exception)
                }
            }
    }

    // Function to handle sign up using Firebase Authentication
    fun signUp() {
        val emailText = email.value.orEmpty()
        val passwordText = password.value.orEmpty()

        if (emailText.isEmpty() || passwordText.isEmpty()) {
            _loginStatus.value = "Please enter email and password to sign up"
            return
        }

        auth.createUserWithEmailAndPassword(emailText, passwordText)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user: FirebaseUser? = auth.currentUser
                    _loginStatus.value = "Sign up successful. Welcome, ${user?.email}"
                    // Navigate to main activity
                } else {
                    _loginStatus.value = "Sign up failed: ${task.exception?.message}"
                    Log.e("SignUp", "Sign up failed", task.exception)
                }
            }
    }
}



