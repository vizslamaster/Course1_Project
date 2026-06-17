package com.udacity.shoestore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

data class Shoe(val name: String)

class ShoeListViewModel : ViewModel() {
    private val _shoeList = MutableLiveData<List<Shoe>>()

    val shoeList: LiveData<List<Shoe>> get() = _shoeList

    init {
        loadShoes()
    }

    private fun loadShoes() {
        val initialShoes = listOf(
            Shoe("Air Max 270"),
            Shoe("Ultraboost 22"),
            Shoe("Classic Leather")
        )
        _shoeList.value = initialShoes
    }
}