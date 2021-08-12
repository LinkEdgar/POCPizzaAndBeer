package com.example.android.turoapp.findpizzaandbeer.repositories

import com.example.android.turoapp.findpizzaandbeer.models.Businesses
import com.example.android.turoapp.findpizzaandbeer.networking.BusinessService
import com.example.android.turoapp.findpizzaandbeer.networking.RetrofitClient
import retrofit2.create

class BusinessRepository {

    private val service = RetrofitClient.getClient().create<BusinessService>()

    suspend fun getBusinesses(type: String, location: String) : Businesses {
        return service.getBusinesses(type, location)
    }
}