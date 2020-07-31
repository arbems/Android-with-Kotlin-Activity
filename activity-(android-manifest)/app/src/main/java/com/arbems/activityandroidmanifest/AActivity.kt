package com.arbems.activityandroidmanifest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class AActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_a)
    }
}