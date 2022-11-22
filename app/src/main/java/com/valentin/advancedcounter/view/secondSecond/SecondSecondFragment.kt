package com.valentin.advancedcounter.view.secondSecond

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.valentin.advancedcounter.R
import com.valentin.advancedcounter.model.data.Counter
import com.valentin.advancedcounter.view.genericAdapter.GenericAdapter
import com.valentin.advancedcounter.view.home.HomeFragmentViewModel
import kotlinx.android.synthetic.main.fragment_second_second.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class SecondSecondFragment : Fragment(R.layout.fragment_second_second) {

    private val viewModel: HomeFragmentViewModel by viewModel()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeData()
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

        counterRvLinear.layoutManager = LinearLayoutManager(activity)
        counterRvLinear.adapter = getAdapter(counters)
    }

    private fun notifyAdapterAboutChanges(counters: MutableList<Counter>) {
        Log.d("DEBUG_TAG", "notify adapter about changes")

        counterRvLinear.adapter?.notifyItemRangeChanged(0, counters.size)
    }

    private fun isAdapterNotAttached(): Boolean {
        return counterRvLinear.adapter == null
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

    private fun getViewHolder(): CounterVHL {
        return CounterVHL.Builder()
            .setOnClickEventListener(::incrementCounter)
            .setOnLongClickEventListener(::navigateToDetailsFragment)
            .build()
    }

    private fun navigateToDetailsFragment(item: Counter): Boolean {
        //what shared prefs? -> used to save clicks amount so i can display in fragment display
        saveToSharedPref(item)
        viewModel.navigateToDetailsFragment(requireActivity())
        return true
    }

    private fun saveToSharedPref(item: Counter) {
        val sharedPref = activity?.getSharedPreferences("shared_pref", Context.MODE_PRIVATE)
        val sharedPrefEdit = sharedPref?.edit()
        sharedPrefEdit?.putString("clicks_amount", item.numberOfClicks.toString())?.apply()
    }
}