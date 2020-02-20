package com.example.beerapp.repository

import androidx.lifecycle.LiveData
import com.example.beerapp.dataSource.ApiDataSource
import com.example.beerapp.model.BeerResponse

class BeerRepository(private val apiDataSource: ApiDataSource) {

    fun getBeers(): LiveData<List<BeerResponse>> {
        getBeerData()
        return apiDataSource.downloadedBeer
    }

    private fun getBeerData() {
        apiDataSource.getBeerData()
    }
}