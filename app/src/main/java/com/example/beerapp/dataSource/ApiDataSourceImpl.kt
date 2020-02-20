package com.example.beerapp.dataSource

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.beerapp.model.BeerResponse
import com.example.beerapp.network.ApiUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApiDataSourceImpl : ApiDataSource {

    private val _downloadedBeer = MutableLiveData<List<BeerResponse>>()
    override val downloadedBeer: LiveData<List<BeerResponse>>
        get() = _downloadedBeer

    override fun getBeerData() {
        ApiUtils.getBeerApiService().getBeerData().enqueue(object : Callback<List<BeerResponse>> {
            override fun onFailure(call: Call<List<BeerResponse>>, t: Throwable) {
                Log.d("Error", "Error: ${t.message}")
            }

            override fun onResponse(
                call: Call<List<BeerResponse>>,
                response: Response<List<BeerResponse>>
            ) {
                Log.d("MyFeedback", "${response.isSuccessful}")
                _downloadedBeer.postValue(response.body())
            }


        })
    }
}