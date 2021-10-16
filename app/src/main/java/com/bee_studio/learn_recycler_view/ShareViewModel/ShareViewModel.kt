package com.bee_studio.learn_recycler_view.ShareViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ShareViewModel:ViewModel() {

var _country=MutableLiveData<String>("Canada")
val country:LiveData<String> = _country

    fun saveCountry( newCountry:String){
        _country.value=newCountry
    }



}