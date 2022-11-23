package com.valentin.advancedcounter.view.home

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.valentin.advancedcounter.R
import com.valentin.advancedcounter.model.data.Counter
import com.valentin.advancedcounter.model.data.MockApiDataResponse
import com.valentin.advancedcounter.model.data.Response
import com.valentin.advancedcounter.model.service.networkService.NetworkService
import com.valentin.advancedcounter.model.service.networkService.NetworkServiceProvider
import com.valentin.advancedcounter.view.genericAdapter.GenericAdapter
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.java.KoinJavaComponent.inject
import retrofit2.Call
import retrofit2.Callback

class HomeFragment : Fragment(R.layout.fragment_home) {

    private val viewModel: HomeFragmentViewModel by viewModel()
    private val networkServiceProvider: NetworkServiceProvider by inject(NetworkService::class.java)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeData()
    }

    private fun observeData() {
        viewModel.getCounters().observe(viewLifecycleOwner) { updateRecyclerViewWithLatestChanges(it) }
    }

    private fun updateRecyclerViewWithLatestChanges(counters: MutableList<Counter>) {
        if (isAdapterNotAttached())
            initAdapter(counters)
        else
            notifyAdapterAboutChanges(counters)
    }

    private fun initAdapter(counters: MutableList<Counter>) {
        counterRv.layoutManager = GridLayoutManager(activity, 2)
        counterRv.adapter = getAdapter(counters)
    }

    private fun notifyAdapterAboutChanges(counters: MutableList<Counter>) {
        counterRv.adapter?.notifyItemRangeChanged(0, counters.size)
    }

    private fun isAdapterNotAttached(): Boolean {
        return counterRv.adapter == null
    }

    private fun incrementCounter(item: Counter) {
        viewModel.incrementCounterAndNotify(item.position)
    }

    private fun getAdapter(counters: MutableList<Counter>): GenericAdapter<Counter> {
        return GenericAdapter.Builder<Counter>()
            .setDataSet(counters)
            .setViewHolder(getViewHolder())
            .build()
    }

    private fun getViewHolder(): CounterVH {
        return CounterVH.Builder()
            .setOnClickEventListener(::incrementCounter)
            .setOnLongClickEventListener(::navigateToDetailsFragment)
            .build()
    }

    private fun navigateToDetailsFragment(item: Counter): Boolean {
        addClicksAmount(item.numberOfClicks)
        viewModel.saveSelectedItemAndNavigateToDetailsFragment(item)
        return true
    }

    private fun addClicksAmount(clicksAmount: Int) {
        networkServiceProvider.addClicksAmount(clicksAmount) {  response ->
            if(response != null) {
                //handle response
                Log.d("ANDROID_DEBUG_TAG", "addClicksAmount() HF: $response")
            } else {
                //handle error
                Log.d("ANDROID_DEBUG_TAG", "response is null")
            }

        }

    }


}
