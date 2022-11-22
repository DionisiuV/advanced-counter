package com.valentin.advancedcounter.view.genericAdapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView

class GenericAdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun <T> bindViewHolderToAdapter(item: T, bindingInterface: GenericAdapterBindingInterface<T>, position: Int) {
        return bindingInterface.bindDataToView(item, itemView, position)
    }
}