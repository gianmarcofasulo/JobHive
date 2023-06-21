package com.example.jobhive.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class HomeViewModel {
    fun createHomeViewModel(): ViewModelProvider.Factory {
        val factory = object : ViewModelProvider.Factory {
            override fun <HomeViewModel : ViewModel> create(modelClass: Class<HomeViewModel>): HomeViewModel {
                @Suppress("UNCHECKED_CAST")
                return com.example.jobhive.screens.usamap.UsaMapViewModel(
                ) as HomeViewModel
            }
        }
        return factory
    }
}
