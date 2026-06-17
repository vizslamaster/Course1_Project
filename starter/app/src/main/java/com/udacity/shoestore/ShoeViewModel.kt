package com.udacity.shoestore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class ShoeViewModel : ViewModel() {

    private val _shoeList = MutableLiveData<MutableList<com.udacity.shoestore.models.Shoe>>(kotlin.collections.mutableListOf())

    val shoeList: LiveData<MutableList<com.udacity.shoestore.models.Shoe>>
        get() = _shoeList

    fun addShoe(shoe: Shoe) {
        _shoeList.value?.add(shoe)
        // Refresh the LiveData value to trigger observers
        _shoeList.value = _shoeList.value
    }
}