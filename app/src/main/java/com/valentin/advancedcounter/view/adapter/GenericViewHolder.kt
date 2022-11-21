package com.valentin.advancedcounter.view.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView

class GenericViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun <T> bind(item: T, bindingInterface: BindingInterface<T>, position: Int) {

        return bindingInterface.bindData(item, itemView, position)
    }

}