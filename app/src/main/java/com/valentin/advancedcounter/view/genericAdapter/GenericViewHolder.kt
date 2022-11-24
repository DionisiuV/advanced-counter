package com.valentin.advancedcounter.view.genericAdapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView

class GenericViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun <T> bindData(item: T, bindingInterface: GenericAdapterBindingInterface<T>, position: Int) {
        return bindingInterface.bindData(item, itemView, position)
    }
}