package com.arbems.fragmentlifecycle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        Log.i("MainActivity", "onStart - ${lifecycle.currentState}")
    }

    override fun onResume() {
        super.onResume()
        Log.i("MainActivity", "onResume - ${lifecycle.currentState}")
    }

    override fun onPause() {
        super.onPause()
        Log.i("MainActivity", "onPause - ${lifecycle.currentState}")
    }

    override fun onStop() {
        super.onStop()
        Log.i("MainActivity", "onStop - ${lifecycle.currentState}")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("MainActivity", "onDestroy - ${lifecycle.currentState}")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i("MainActivity", "onRestart - ${lifecycle.currentState}")
    }

    override fun onBackPressed() {
        super.onBackPressed()
        Log.i("MainActivity", "onBackPressed - ${lifecycle.currentState}")
    }
}