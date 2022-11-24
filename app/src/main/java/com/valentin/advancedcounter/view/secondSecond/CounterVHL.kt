package com.valentin.advancedcounter.view.secondSecond

import android.view.View
import com.valentin.advancedcounter.R
import com.valentin.advancedcounter.model.data.Counter
import com.valentin.advancedcounter.view.genericAdapter.GenericAdapterBindingInterface
import kotlinx.android.synthetic.main.counter_item.view.*


//what does this mean - VHL? does this class have cancer? - view holder linear -> just to differentiate them
//how does this class differ from Counter VH? - made just for practice, no changes/ maybe i will handle click events in the other way
class CounterVHL private constructor() : GenericAdapterBindingInterface<Counter> {

    private var onClickEventListener: ((Counter) -> Unit) = { _ -> }
    private var onLongClickEventListener: ((Counter) -> Boolean) = { false }


    override fun bindData(item: Counter, view: View, position: Int) {
        view.clicksAmountTv.text = item.numberOfClicks.toString()
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
        private val counterVHL = CounterVHL()

        fun setOnClickEventListener(clickAction: (Counter) -> Unit): Builder {
            this.counterVHL.onClickEventListener = clickAction

            return this
        }

        fun setOnLongClickEventListener(onLongClickAction: ((Counter) -> Boolean)): Builder {
            this.counterVHL.onLongClickEventListener = onLongClickAction

            return this
        }

        fun build(): CounterVHL {
            return counterVHL
        }
    }
}