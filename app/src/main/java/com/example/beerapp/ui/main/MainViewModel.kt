package com.example.beerapp.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.beerapp.dataSource.ApiDataSourceImpl
import com.example.beerapp.model.BeerResponse
import com.example.beerapp.repository.BeerRepository

class MainViewModel : ViewModel() {
    private val beerDataSource = ApiDataSourceImpl()
    private val repo = BeerRepository(beerDataSource)

    val observableBeers: LiveData<List<BeerResponse>>
        get() = getBeers()

    private fun getBeers(): LiveData<List<BeerResponse>> {
        return repo.getBeers()
    }
}
