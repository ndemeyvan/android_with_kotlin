package com.bee_studio.learn_recycler_view.BasicViewModel

import androidx.lifecycle.ViewModel

class BasicViewModel : ViewModel() {
    var num =0
    fun addNumber(){
        num++
    }
}