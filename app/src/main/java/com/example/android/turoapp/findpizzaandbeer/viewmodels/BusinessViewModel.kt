package com.example.android.turoapp.findpizzaandbeer.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.turoapp.findpizzaandbeer.Resource
import com.example.android.turoapp.findpizzaandbeer.Status
import com.example.android.turoapp.findpizzaandbeer.models.Business
import com.example.android.turoapp.findpizzaandbeer.models.Businesses
import com.example.android.turoapp.findpizzaandbeer.repositories.BusinessRepository
import kotlinx.coroutines.launch
import java.util.*

class BusinessViewModel(private val businessRepo: BusinessRepository): ViewModel() {

    private var businesses: MutableLiveData<Resource<List<Business>>>? = null

    private val location = "111 Sutter St #1300, San Francisco"
    private val typePizza = "pizza"
    private val typeBeer = "beer"

    fun getBusinesses() : LiveData<Resource<List<Business>>>?{
        if (businesses == null) {
            businesses = MutableLiveData()
            businesses?.value = Resource(Status.LOADING,null,"Loading")
            viewModelScope.launch {
                val pizza = getPizzaPlaces()
                val beers = getBeerPlaces()
                when {
                    pizza == null && beers == null -> {
                        businesses?.postValue(Resource.error("Error loading businesses", null))
                    }
                    pizza != null && beers != null -> {
                        businesses?.postValue(Resource.success(getCombinedList(pizza.businesses, beers.businesses)))
                    }
                    pizza != null -> {
                        businesses?.postValue(Resource.error("Unable to load beer restaurants", pizza.businesses))
                    }
                    beers != null -> {
                        businesses?.postValue(Resource.error("Unable to load pizza restaurants", beers.businesses))
                    }
                }
            }
        }

        return businesses
    }

    private fun getCombinedList(pizzas: List<Business>?, beers: List<Business>?) : List<Business>{
        val list = ArrayList<Business>()
        if(pizzas != null) list.addAll(pizzas)
        if(beers != null) list.addAll(beers)
        return list
    }



    suspend fun getPizzaPlaces(type: String = this.typePizza, location: String = this.location) : Businesses? {
        return try {
            businessRepo.getBusinesses(type, location)
        } catch (e : Exception) {
            Log.e("getPizzaPlaces", "--> ${e.localizedMessage}")
            null
        }
    }

    suspend fun getBeerPlaces(type: String = this.typeBeer, location: String = this.location) : Businesses? {
        return try {
            businessRepo.getBusinesses(type, location)
        } catch (e : Exception) {
            Log.e("getBeerPlaces", "--> ${e.localizedMessage}")
            null
        }
    }
}