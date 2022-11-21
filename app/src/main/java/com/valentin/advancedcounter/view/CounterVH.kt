package com.valentin.advancedcounter.view

import android.view.View
import com.valentin.advancedcounter.model.data.Counter
import kotlinx.android.synthetic.main.counter_item.view.*


class CounterVH private constructor() : GenericAdapter.BindingInterface<Counter> {

    private var onClickEventListener: ((Counter) -> Unit) = { _ -> }
    private var onLongClickEventListener: ((Counter) -> Boolean) = { false }


    class Builder {
        private val counterVH = CounterVH()

        fun setOnClickEventListener(clickAction: (Counter) -> Unit): Builder {
            this.counterVH.onClickEventListener = clickAction

            return this
        }

        fun setOnLongClickEventListener(onLongClickAction: ((Counter) -> Boolean) ): Builder {
            this.counterVH.onLongClickEventListener = onLongClickAction

            return this
        }

        fun build(): CounterVH {
            return counterVH
        }

    }


    private fun setEvents(item: Counter, itemView: View) {

        itemView.setOnClickListener { onClickEventListener(item) }
        itemView.setOnLongClickListener { onLongClickEventListener(item) }

    }

    override fun bindData(item: Counter, view: View, position: Int) {

        view.clicksAmountTv.text = item.numberOfClicks.toString()
        item.position = position
        setEvents(item, view)

    }


}