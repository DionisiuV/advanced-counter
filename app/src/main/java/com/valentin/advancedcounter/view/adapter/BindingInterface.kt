package com.valentin.advancedcounter.view.adapter

import android.view.View

interface BindingInterface<T> {

    fun bindData(item: T, view: View, position: Int)
    fun getLayoutResId(): Int

}