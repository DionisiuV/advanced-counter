package com.valentin.advancedcounter.view.secondSecond

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.valentin.advancedcounter.R
import com.valentin.advancedcounter.model.data.Counter
import com.valentin.advancedcounter.view.genericAdapter.GenericAdapter
import kotlinx.android.synthetic.main.fragment_second_second.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class SecondSecondFragment : Fragment(R.layout.fragment_second_second) {

    private val viewModel: SecondSecondFragmentViewModel by viewModel()


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
        counterRvLinear.layoutManager = LinearLayoutManager(activity)
        counterRvLinear.adapter = getAdapter(counters)
    }

    private fun notifyAdapterAboutChanges(counters: MutableList<Counter>) {
        counterRvLinear.adapter?.notifyItemRangeChanged(0, counters.size)
    }

    private fun isAdapterNotAttached(): Boolean {
        return counterRvLinear.adapter == null
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

    private fun getViewHolder(): CounterVHL {
        return CounterVHL.Builder()
            .setOnClickEventListener(::incrementCounter)
            .setOnLongClickEventListener(::navigateToDetailsFragment)
            .build()
    }

    private fun navigateToDetailsFragment(item: Counter): Boolean {
        viewModel.saveSelectedItemAndNavigateToDetailsFragment(item)
        return true
    }
}