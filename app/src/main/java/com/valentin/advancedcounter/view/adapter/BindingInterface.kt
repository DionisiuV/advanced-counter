package com.valentin.advancedcounter.view.adapter

import android.view.View

interface BindingInterface<T> {//1

    fun bindData(item: T, view: View, position: Int)//2

    fun getLayoutResId(): Int

}
// 1+2 naming unclear: binding what? what it connects to?


//package name could be different