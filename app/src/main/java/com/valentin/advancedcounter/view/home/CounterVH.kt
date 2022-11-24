package com.valentin.advancedcounter.view.home

import android.view.View
import com.valentin.advancedcounter.R
import com.valentin.advancedcounter.model.data.Counter
import com.valentin.advancedcounter.view.genericAdapter.GenericAdapterBindingInterface
import kotlinx.android.synthetic.main.counter_item.view.*


class CounterVH private constructor() : GenericAdapterBindingInterface<Counter> {

    private var onClickEventListener: ((Counter) -> Unit) = { _ -> }
    private var onLongClickEventListener: ((Counter) -> Boolean) = { false }


    override fun bindData(item: Counter, view: View, position: Int) {
        setItemToTv(item, view)
        setItemPosition(item, position)
        setClickEvents(item, view)
    }

    override fun getLayoutResId(): Int {
        return R.layout.counter_item
    }

    private fun setClickEvents(item: Counter, itemView: View) {
        itemView.setOnClickListener { onClickEventListener(item) }
        itemView.setOnLongClickListener { onLongClickEventListener(item) }
    }

    private fun setItemPosition(item: Counter, position: Int) {
        item.position = position
    }

    private fun setItemToTv(item: Counter, view: View) {
        view.clicksAmountTv.text = item.numberOfClicks.toString()
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