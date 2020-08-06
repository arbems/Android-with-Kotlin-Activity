package com.arbems.fragmentwithviewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel: ViewModel() {
    private val _selected = MutableLiveData<Boolean>()
    val selected: LiveData<Boolean> get() = _selected

    fun select(selected: Boolean) {
        _selected.value = selected
    }
}