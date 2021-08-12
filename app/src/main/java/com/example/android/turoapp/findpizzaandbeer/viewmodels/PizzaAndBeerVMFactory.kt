package com.example.android.turoapp.findpizzaandbeer.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android.turoapp.findpizzaandbeer.repositories.BusinessRepository
import java.lang.IllegalArgumentException
import java.security.Provider

class PizzaAndBeerVMFactory (val repo: BusinessRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BusinessViewModel::class.java)) {
            return BusinessViewModel(repo) as T
        }
        throw IllegalArgumentException("Unknown viewmodel class")
    }


}