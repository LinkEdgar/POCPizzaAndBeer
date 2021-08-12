package com.example.android.turoapp.findpizzaandbeer.networking

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private const val base_url = "https://api.yelp.com"

    private val httpClient = OkHttpClient.Builder()
    private val client: Retrofit

    init {
        httpClient.addInterceptor { chain ->
            val original = chain.request()
            val request = original.newBuilder()
                    .header("Authorization", "Bearer 2ROaa2Rh9qu3WVTCms8FoVE4mSfHQHC7QJua95-kKT-PqzIlLSrs4tmHVdtdFw_66-JNfRiJmbCByHTvFNy5dQq-tpfS4FrPpupIzKlgELR3br-r5trpeFhrCRgwWnYx")
                    .method(original.method(), original.body())
                    .build()
            chain.proceed(request)
        }

        client = Retrofit.Builder().client(httpClient.build()).baseUrl(base_url).addConverterFactory(GsonConverterFactory.create()).build()
    }

    fun getClient() = client
}