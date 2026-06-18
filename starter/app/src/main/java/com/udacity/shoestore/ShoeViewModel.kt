package com.udacity.shoestore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class ShoeViewModel : ViewModel() {

    private val _shoeList = MutableLiveData<MutableList<com.udacity.shoestore.models.Shoe>>(kotlin.collections.mutableListOf())

    val shoeList: LiveData<MutableList<com.udacity.shoestore.models.Shoe>>
        get() = _shoeList

    init {
        loadShoes()
    }

    private fun loadShoes() {
        val initialShoes = mutableListOf(
            Shoe("Air Max 270", "Nike", "270", "Best for running"),
            Shoe("Ultraboost 22", "Reebok", "350", "Best for walking"),
            Shoe("Classic Leather", "Adidas", "380", "Best for gym wear")
        )
        _shoeList.value = initialShoes
    }

    fun addShoe(newShoe: Shoe) {
        _shoeList.value?.add(newShoe)
        _shoeList.value = _shoeList.value
    }
}