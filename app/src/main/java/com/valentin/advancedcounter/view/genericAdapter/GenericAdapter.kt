package com.valentin.advancedcounter.view.genericAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class GenericAdapter<T> private constructor() : RecyclerView.Adapter<GenericViewHolder>() {

    private var dataSet: List<T> = listOf()
    private lateinit var bindingInterface: GenericAdapterBindingInterface<T>


    private fun setViewHolder(bindingInterface: GenericAdapterBindingInterface<T>) {
        this.bindingInterface = bindingInterface
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenericViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(bindingInterface.getLayoutResId(), parent, false)

        return GenericViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: GenericViewHolder, position: Int) {
        holder.bindDataToViewHolder(dataSet[position], bindingInterface, position)
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    private fun setDataSet(dataSet: List<T>) {
        this.dataSet = dataSet
        notifyItemRangeInserted(0, dataSet.size)
    }


    class Builder<T> {

        private val genericAdapter = GenericAdapter<T>()


        fun setDataSet(dataSet: List<T>): Builder<T> {
            genericAdapter.setDataSet(dataSet)

            return this
        }

        fun setViewHolder(bindingInterface: GenericAdapterBindingInterface<T>): Builder<T> {
            genericAdapter.setViewHolder(bindingInterface)

            return this
        }

        fun build(): GenericAdapter<T> {
            return genericAdapter
        }

    }
}