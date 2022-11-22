package com.valentin.advancedcounter.view.genericAdapter

import android.view.View

//to rename bindingInterface and bindData
//rename package
interface GenericAdapterBindingInterface<T> {

    fun bindDataToView(item: T, view: View, position: Int)

    fun getLayoutResId(): Int
}