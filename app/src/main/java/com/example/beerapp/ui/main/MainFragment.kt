package com.example.beerapp.ui.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.beerapp.R
import com.example.beerapp.adapter.BeerListAdapter
import com.example.beerapp.model.BeerResponse
import com.example.beerapp.model.NavigationParam
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    private lateinit var beerListAdapter: BeerListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        activity?.let {
            viewModel = ViewModelProvider(it).get(MainViewModel::class.java)
            recycle_view.layoutManager = LinearLayoutManager(this.context)
            recycle_view.setHasFixedSize(true)
            beerListAdapter = BeerListAdapter()
            recycle_view.adapter = beerListAdapter
            viewModel.observableBeers.observe(viewLifecycleOwner, Observer { beerResponse ->
                renderView(beerResponse)

            })

            beerListAdapter.setOnItemClickListener(object : BeerListAdapter.OnItemClickListener {
                override fun onItemClick(beerResponse: BeerResponse, view: View) {
                    val navParam = NavigationParam(
                        beerResponse.description,
                        beerResponse.name,
                        beerResponse.image_url,
                        beerResponse.brewers_tips
                    )
                    view.findNavController().navigate(MainFragmentDirections.gotoDetail(navParam))
                }

            })
        }
    }

    private fun renderView(beerList: List<BeerResponse>) {
        beerListAdapter.submitList(beerList)
        if (beerList.isEmpty()) {
            recycle_view.visibility = View.GONE
            txtLoading.visibility = View.VISIBLE
        } else {
            recycle_view.visibility = View.VISIBLE
            txtLoading.visibility = View.GONE
        }
    }

}
