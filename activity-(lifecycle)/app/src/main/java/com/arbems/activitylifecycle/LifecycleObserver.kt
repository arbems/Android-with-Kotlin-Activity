package com.arbems.activitylifecycle

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

class LifecycleObserver(
    private val tag: String,
    private val lifecycle: Lifecycle

) : LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        Log.i(tag, "Lifecycle.Event.ON_CREATE, state ${lifecycle.currentState}")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart() {
        Log.i(tag, "Lifecycle.Event.ON_START, state ${lifecycle.currentState}")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume() {
        Log.i(tag, "Lifecycle.Event.ON_RESUME, state ${lifecycle.currentState}")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause() {
        Log.i(tag, "Lifecycle.Event.ON_PAUSE, state ${lifecycle.currentState}")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStop() {
        Log.i(tag, "Lifecycle.Event.ON_STOP, state ${lifecycle.currentState}")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {
        Log.i(tag, "Lifecycle.Event.ON_DESTROY, state ${lifecycle.currentState}")
    }
}