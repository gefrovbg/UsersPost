package com.example.userspost.presentation

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.userspost.R
import com.example.userspost.databinding.ActivityMainBinding
import com.example.userspost.utilits.NetworkConnection
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    private lateinit var cld : NetworkConnection

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        checkNetworkConnection()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    @SuppressLint("ResourceAsColor")
    private fun checkNetworkConnection() {
        cld = NetworkConnection(application)

        cld.observe(this) { isConnected ->

            booleanConnection.value = isConnected

        }

    }

    companion object{
        private val booleanConnection = MutableLiveData<Boolean>()
        val showConnectionBoolean: LiveData<Boolean> = booleanConnection
    }
}