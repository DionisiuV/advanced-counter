package com.valentin.advancedcounter.view.secondSecond

import android.view.View
import com.valentin.advancedcounter.model.data.Counter
import com.valentin.advancedcounter.view.adapter.BindingInterface
import kotlinx.android.synthetic.main.counter_item.view.*

//what does this mean - VHL? does this class have cancer?
//how does this class differ from Counter VH?
class CounterVHL private constructor() : BindingInterface<Counter> {

    private var onClickEventListener: ((Counter) -> Unit) = { _ -> }
    private var onLongClickEventListener: ((Counter) -> Boolean) = { false }
    private var layoutResourceId: Int = 0


    class Builder {//move this either top or bottom of the file - recommend bottom
        private val counterVH = CounterVHL()

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

        fun build(): CounterVHL {
            return counterVH
        }
    }

    override fun bindData(item: Counter, view: View, position: Int) {
        view.clicksAmountTv.text = item.numberOfClicks.toString()//oops!!
        item.position = position

        setClickEvents(item, view)
    }

    override fun getLayoutResId(): Int {
        return this.layoutResourceId
    }

    private fun setClickEvents(item: Counter, itemView: View) {
        itemView.setOnClickListener { onClickEventListener(item) }
        itemView.setOnLongClickListener { onLongClickEventListener(item) }
    }

    private fun setLayoutResId(layoutResId: Int) {
        this.layoutResourceId = layoutResId// you don't need 'layoutResourceId' global param; you don't need setter for it; you can override this method dirrectly to 'return R.layout.whatever_layout'

    }



}