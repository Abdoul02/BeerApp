package com.example.beerapp.network

import com.example.beerapp.model.BeerResponse
import retrofit2.Call
import retrofit2.http.GET

interface BeerApiService {

    @GET("v2/beers")
    fun getBeerData(): Call<List<BeerResponse>>
}