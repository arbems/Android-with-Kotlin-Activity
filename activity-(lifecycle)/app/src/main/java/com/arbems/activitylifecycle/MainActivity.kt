package com.arbems.activitylifecycle

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val lifecycleObserver = LifecycleObserver("MainActivity", lifecycle)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lifecycle.addObserver(lifecycleObserver)

        button.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }

        buttonFinish.setOnClickListener {
            finish()
        }

        Log.i("MainActivity", "onCreate! state: ${lifecycle.currentState}")
    }

    override fun onStart() {
        super.onStart()
        Log.i("MainActivity", "onStart! state: ${lifecycle.currentState}")
    }

    override fun onResume() {
        super.onResume()

        Log.i("MainActivity", "onResume! state: ${lifecycle.currentState}")
    }

    override fun onPause() {
        super.onPause()

        Log.i("MainActivity", "onPause! state: ${lifecycle.currentState}")
    }

    override fun onStop() {
        super.onStop()

        Log.i("MainActivity", "onStop! state: ${lifecycle.currentState}")
    }

    override fun onDestroy() {
        super.onDestroy()

        Log.i("MainActivity", "onDestroy! state: ${lifecycle.currentState}")
    }

    override fun onRestart() {
        super.onRestart()

        Log.i("MainActivity", "onRestart! state: ${lifecycle.currentState}")
    }

    override fun onBackPressed() {
        super.onBackPressed()

        Log.i("MainActivity", "onBackPressed! state: ${lifecycle.currentState}")
    }
}