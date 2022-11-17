package com.valentin.advancedcounter.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.valentin.advancedcounter.R
import com.valentin.advancedcounter.model.data.Counter
import kotlinx.android.synthetic.main.counter_item.view.*

class GeneralAdapter<T> : RecyclerView.Adapter<GeneralViewHolder<T>>() {

    private lateinit var dataSet: List<T>
    private lateinit var contract: VhInterface<T>

    fun setData(newData: ArrayList<T>) {
        this.dataSet = newData
    }

    fun setViewHolder(contract: VhInterface<T>) {
        this.contract = contract
    }

    override fun getItemCount() = dataSet.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GeneralViewHolder<T> {
        val itemView = LayoutInflater.from(parent.context).inflate(contract.getViewResource(), parent, false)
        return GeneralViewHolder(itemView, contract)
    }

    override fun onBindViewHolder(holder: GeneralViewHolder<T>, position: Int) {
        holder.bind(dataSet[position], position)
    }
}

class GeneralViewHolder<T>(itemView: View, private val contract: VhInterface<T>) : RecyclerView.ViewHolder(itemView) {

    fun bind(item: T, position: Int) {
        contract.bind(itemView, item, position)
    }

}

interface VhInterface<T> {

    fun bind(itemView: View, item: T, position: Int)

    //need to check this
    fun setClickListener(event: String, listener: (T) -> Unit)

    fun getViewResource(): Int
}

class CounterAdapterVH : VhInterface<Counter> {

    private lateinit var counterClickListener: (Counter) -> Unit
    private lateinit var counterLongClickListener: (Counter) -> Unit

    override fun getViewResource(): Int {
        return R.layout.counter_item
    }
    override fun setClickListener(event: String, listener: (Counter) -> Unit) {
        when(event) {
            "onClick" -> counterClickListener = listener
            "onLongClick" -> counterLongClickListener = listener
        }
    }

    override fun bind(itemView: View, item: Counter, position: Int) {
        itemView.clicksAmountTv.text = item.numberOfClicks.toString()
        item.position = position

        itemView.setOnClickListener { counterClickListener(item) }

        //maybe a good fix?
        if(this::counterLongClickListener.isInitialized) {
            // problem if onLongClick is not set
            itemView.setOnLongClickListener {
                counterLongClickListener(item)
                true
            }
        }

    }
}