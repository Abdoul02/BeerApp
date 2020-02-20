package com.example.beerapp.dataSource

import androidx.lifecycle.LiveData
import com.example.beerapp.model.BeerResponse

interface ApiDataSource {

    val downloadedBeer: LiveData<List<BeerResponse>>
    fun getBeerData()

}