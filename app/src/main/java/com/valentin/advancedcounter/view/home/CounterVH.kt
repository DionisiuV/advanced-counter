package com.valentin.advancedcounter.view.home

import android.view.View
import com.valentin.advancedcounter.R
import com.valentin.advancedcounter.model.data.Counter
import com.valentin.advancedcounter.view.genericAdapter.GenericAdapterBindingInterface
import kotlinx.android.synthetic.main.counter_item.view.*


class CounterVH private constructor() : GenericAdapterBindingInterface<Counter> {

    private var onClickEventListener: ((Counter) -> Unit) = { _ -> }
    private var onLongClickEventListener: ((Counter) -> Boolean) = { false }


    override fun bindDataToView(item: Counter, view: View, position: Int) {
        view.clicksAmountTv.text = item.numberOfClicks.toString()//oops!! -> ?? make sense to have a function for this?
        item.position = position
        setClickEvents(item, view)
    }

    override fun getLayoutResId(): Int {
        return R.layout.counter_item
    }

    private fun setClickEvents(item: Counter, itemView: View) {
        itemView.setOnClickListener { onClickEventListener(item) }
        itemView.setOnLongClickListener { onLongClickEventListener(item) }
    }


    class Builder {
        private val counterVH = CounterVH()


        fun setOnClickEventListener(clickAction: (Counter) -> Unit): Builder {
            this.counterVH.onClickEventListener = clickAction

            return this
        }

        fun setOnLongClickEventListener(onLongClickAction: ((Counter) -> Boolean)): Builder {
            this.counterVH.onLongClickEventListener = onLongClickAction

            return this
        }


        fun build(): CounterVH {
            return counterVH
        }
    }
}