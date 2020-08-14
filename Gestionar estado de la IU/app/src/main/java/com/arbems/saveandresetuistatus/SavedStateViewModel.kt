package com.arbems.saveandresetuistatus

import android.app.Application
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.*

class SavedStateViewModel(private val mState: SavedStateHandle): ViewModel() {

    companion object {
        const val SCORE_KEY = "score_key"

        fun getViewModel(
            application: Application,
            fragmentActivity: FragmentActivity
        ): SavedStateViewModel {
            val defaultState = Bundle().apply { putInt(SCORE_KEY, 0) }
            val factory = SavedStateViewModelFactory(application, fragmentActivity, defaultState)
            return ViewModelProvider(fragmentActivity, factory).get(SavedStateViewModel::class.java)
        }
    }

    private val _score = MutableLiveData<Int>()
    val score: LiveData<Int> get() = _score

    fun setScore(score: Int?) {
        _score.value = score
        mState?.set(SCORE_KEY, score);
    }

    init {
        _score.value = 0
        if(mState != null) {
            val scoreSaved: LiveData<Int> = mState!!.getLiveData(SCORE_KEY)
            if(scoreSaved.value?.toInt()!! > 0) _score.value = scoreSaved.value
        }
    }

}