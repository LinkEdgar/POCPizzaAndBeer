package com.example.android.turoapp.findpizzaandbeer.networking

import com.example.android.turoapp.findpizzaandbeer.models.Businesses
import retrofit2.http.GET
import retrofit2.http.Query

interface BusinessService {

    @GET("v3/businesses/search")
    suspend fun getBusinesses(@Query("term") type: String, @Query("location") location: String) : Businesses
}