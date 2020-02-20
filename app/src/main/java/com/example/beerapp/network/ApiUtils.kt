package com.example.beerapp.network

object ApiUtils {

    private const val BASE_URL = "https://api.punkapi.com/"

    fun getBeerApiService(): BeerApiService {
        return RetrofitClient.getClient(BASE_URL)!!.create(BeerApiService::class.java)
    }
}