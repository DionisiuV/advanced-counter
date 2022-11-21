package com.valentin.advancedcounter.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.valentin.advancedcounter.view.GenericAdapter.*

class GenericAdapter<T> private constructor(): RecyclerView.Adapter<GenericViewHolder>() {


    private var dataSet: List<T> = listOf()

    set(value) {
        field = value
        notifyDataSetChanged()//check out this warning
    }

    private lateinit var bindingInterface: BindingInterface<T>
    private var layoutResId: Int = 0


    private fun setData(dataSet: List<T>) {
        this.dataSet = dataSet
    }

    private fun setLayoutResId(layoutResId: Int) {
        this.layoutResId = layoutResId
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

        fun setLayoutResId(layoutResId: Int) : Builder<T> {
            genericAdapter.setLayoutResId(layoutResId)

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


//new file
    class GenericViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun <T> bind(item: T, bindingInterface: BindingInterface<T>, position: Int) {

            return bindingInterface.bindData(item, itemView, position)
        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenericViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(layoutResId, parent, false)

        return GenericViewHolder(itemView)
    }


    override fun onBindViewHolder(holder: GenericViewHolder, position: Int) {

        holder.bind(dataSet[position], bindingInterface, position)

    }


    override fun getItemCount(): Int {

        return dataSet.size
    }

//new file - add to appropriate package
    interface BindingInterface<T> {

        fun bindData(item: T, view: View, position: Int)

    }


}