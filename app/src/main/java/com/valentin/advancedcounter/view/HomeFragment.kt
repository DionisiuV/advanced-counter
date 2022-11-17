package com.valentin.advancedcounter.view

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.valentin.advancedcounter.R
import com.valentin.advancedcounter.model.data.Counter
import com.valentin.advancedcounter.viewModel.HomeFragmentViewModel
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(R.layout.fragment_home) {

    private val viewModel by viewModels<HomeFragmentViewModel>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeData()
    }

    private fun incrementCounter(item: Counter) {
        viewModel.incrementCounter(item.position)
    }

    private fun goToFragmentDetails(item: Counter) {
        //navigate to fragment details with item info
        Log.d("DEBUG_TAG", "navigate to fragment details with item info: ${item}")
    }

    private fun observeData() {
        viewModel.getCounters().observe(viewLifecycleOwner) { counters ->
            counterRv.adapter = getAdapter(counters)
            counterRv.adapter?.notifyItemRangeInserted(0, counters.size - 1)
        }
    }

    private fun getAdapter(counters:MutableList<Counter>):GeneralAdapter<Counter>{
        val vh = CounterAdapterVH()
        vh.setClickListener("onClick", ::incrementCounter)
        vh.setClickListener("onLongClick", ::goToFragmentDetails)
        return GeneralAdapter<Counter>().also {
            it.setData(ArrayList(counters.toList()))
            it.setViewHolder(vh)
        }
    }
}