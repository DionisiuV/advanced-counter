package com.valentin.advancedcounter.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.valentin.advancedcounter.view.adapter.GenericAdapter.*

class GenericAdapter<T> private constructor(): RecyclerView.Adapter<GenericViewHolder>() {

    private var dataSet: List<T> = listOf()
    set(value) {
        field = value
        notifyItemRangeInserted(0, dataSet.size)//check out this warning
    }

    private lateinit var bindingInterface: BindingInterface<T>


    private fun setData(dataSet: List<T>) {
        this.dataSet = dataSet
    }

    private fun setViewHolder(bindingInterface: BindingInterface<T>) {
        this.bindingInterface = bindingInterface
    }


    class Builder<T> {

        private val genericAdapter = GenericAdapter<T>()


        fun setData(dataSet: List<T>) : Builder<T> {
            genericAdapter.setData(dataSet)

            return this
        }

        fun setViewHolder(bindingInterface: BindingInterface<T>) : Builder<T> {
            genericAdapter.setViewHolder(bindingInterface)

            return this
        }

        fun build() : GenericAdapter<T> {
            return genericAdapter
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenericViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(bindingInterface.getLayoutResId(), parent, false)

        return GenericViewHolder(itemView)
    }


    override fun onBindViewHolder(holder: GenericViewHolder, position: Int) {
        holder.bind(dataSet[position], bindingInterface, position)
    }


    override fun getItemCount(): Int {
        return dataSet.size
    }
}