package com.example.android.turoapp.findpizzaandbeer.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.example.android.turoapp.R
import com.example.android.turoapp.findpizzaandbeer.Status
import com.example.android.turoapp.findpizzaandbeer.adapters.PizzaAndBeerAdapter
import com.example.android.turoapp.findpizzaandbeer.models.Business
import com.example.android.turoapp.findpizzaandbeer.repositories.BusinessRepository
import com.example.android.turoapp.findpizzaandbeer.viewmodels.BusinessViewModel
import com.example.android.turoapp.findpizzaandbeer.viewmodels.PizzaAndBeerVMFactory

class MainActivity : AppCompatActivity() {

    //injected via DI
    private val factory = PizzaAndBeerVMFactory(BusinessRepository())
    private val viewModel: BusinessViewModel by viewModels { factory }

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: PizzaAndBeerAdapter
    private lateinit var progressBar: ProgressBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initUi()

        observePizzaAndBeers()
    }

    private fun observePizzaAndBeers() {
        viewModel.getBusinesses()?.observe(this, Observer { resource ->
            when(resource.status) {
                Status.LOADING -> progressBar.visibility = View.VISIBLE
                Status.SUCCESS -> updateUi(resource?.data,null)
                Status.ERROR -> updateUi(resource?.data, resource.message)
            }
        })
    }

    private fun updateUi(data: List<Business>?, errorMessage: String?) {
        progressBar.visibility = View.GONE
        data?.let {
            adapter.data.addAll(it)
            adapter.notifyDataSetChanged()
        }
        if (errorMessage != null) {
            Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
        }

    }

    private fun initUi() {
        progressBar = findViewById(R.id.progress_bar)
        recyclerView = findViewById(R.id.recyclerview)
        adapter = PizzaAndBeerAdapter()
        recyclerView.adapter = adapter
    }
}