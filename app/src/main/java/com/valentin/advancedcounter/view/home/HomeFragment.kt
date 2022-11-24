package com.valentin.advancedcounter.view.home

import android.os.Bundle
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
    }

    private fun observeData() {
        viewModel.getCounters().observe(viewLifecycleOwner) { updateRecyclerViewWithLatestChanges(it) }
    }

    private fun updateRecyclerViewWithLatestChanges(counters: MutableList<Counter>) {
        if (adapterIsNotAttached())
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

    private fun adapterIsNotAttached(): Boolean {
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
        viewModel.saveSelectedItemAndNavigateToDetailsFragment(item)
        return true
    }
}
