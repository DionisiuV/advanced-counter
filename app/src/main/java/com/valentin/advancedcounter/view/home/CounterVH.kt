package com.valentin.advancedcounter.view.home

import android.view.View
import com.valentin.advancedcounter.model.data.Counter
import com.valentin.advancedcounter.view.adapter.BindingInterface
import kotlinx.android.synthetic.main.counter_item.view.*
import kotlin.reflect.KFunction1


class CounterVH private constructor() : BindingInterface<Counter> {

    private var onClickEventListener: ((Counter) -> Unit) = { _ -> }
    private var onLongClickEventListener: ((Counter) -> Boolean) = { false }
    private var layoutResourceId: Int = 0


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

        fun setLayoutResId(layoutResId: Int): Builder {
            counterVH.setLayoutResId(layoutResId)

            return this
        }

        fun build(): CounterVH {
            return counterVH
        }
    }

    private fun setClickEvents(item: Counter, itemView: View) {
        itemView.setOnClickListener { onClickEventListener(item) }
        itemView.setOnLongClickListener { onLongClickEventListener(item) }
    }

    private fun setLayoutResId(layoutResId: Int) {
        this.layoutResourceId = layoutResId
    }

    override fun bindData(item: Counter, view: View, position: Int) {
        view.clicksAmountTv.text = item.numberOfClicks.toString()
        item.position = position
        setClickEvents(item, view)
    }

    override fun getLayoutResId(): Int {
        return this.layoutResourceId
    }
}