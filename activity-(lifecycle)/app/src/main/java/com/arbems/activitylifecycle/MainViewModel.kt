package com.arbems.activitylifecycle

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {
    private val _textMsg = MutableLiveData<String>()
    val textMsg: LiveData<String> get() = _textMsg

    init {
        _textMsg.value = ""
    }

    fun setTextMsg(text: String): String? {
        _textMsg.value = _textMsg.value + "\n$text"

        return textMsg.value
    }

    fun reset() {
        _textMsg.value = ""
    }
}