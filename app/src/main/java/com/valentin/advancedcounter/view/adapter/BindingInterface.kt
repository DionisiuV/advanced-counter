package com.valentin.advancedcounter.view.adapter

import android.view.View

//to rename bindingInterface and bindData
//rename package
interface BindingInterface<T> {

    fun bindData(item: T, view: View, position: Int)
    fun getLayoutResId(): Int

}