package com.valentin.advancedcounter.view.home

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.valentin.advancedcounter.R
import com.valentin.advancedcounter.model.data.Counter
import com.valentin.advancedcounter.view.genericAdapter.GenericAdapter
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment(R.layout.fragment_home) {

    private val viewModel: HomeFragmentViewModel by viewModel()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeData()
//        getClicksAmountFromSharedPref()  //what shared prefs? - you don't need them -> testing purpose
    }

    private fun observeData() {
        viewModel.getCounters().observe(viewLifecycleOwner) { provideAdapterWithData(it) }
    }

    private fun provideAdapterWithData(counters: MutableList<Counter>) {
        if (isAdapterNotAttached())
            initAdapter(counters)
        else
            notifyAdapterAboutChanges(counters)
    }

    private fun initAdapter(counters: MutableList<Counter>) {
        Log.d("DEBUG_TAG", "counterRv Adapter not set, setting rn")
        counterRv.layoutManager = GridLayoutManager(activity, 2)
        counterRv.adapter = getAdapter(counters)
    }

    private fun notifyAdapterAboutChanges(counters: MutableList<Counter>) {
        Log.d("DEBUG_TAG", "notify adapter about changes")
        counterRv.adapter?.notifyItemRangeChanged(0, counters.size)
    }

    private fun isAdapterNotAttached(): Boolean {
        return counterRv.adapter == null
    }

    private fun incrementCounter(item: Counter) {
        viewModel.incrementCounter(item.position)
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
        //what shared prefs? -> used to save clicks amount so i can display in fragment details
        saveToSharedPref(item)
        Log.d("DEBUG_TAG", "navigateToDetailsFragment() clicks amount: ${item.numberOfClicks}")
        viewModel.navigateToDetailsFragment(requireActivity())
        return true
    }

    private fun saveToSharedPref(item: Counter) {
        val sharedPref = activity?.getSharedPreferences("shared_pref", Context.MODE_PRIVATE)
        val sharedPrefEdit = sharedPref?.edit()
        Log.d("DEBUG_TAG", "saveToSharedPref() clicks amount: ${item.numberOfClicks}")
        sharedPrefEdit?.putString("clicks_amount", item.numberOfClicks.toString())?.apply()
    }
}
