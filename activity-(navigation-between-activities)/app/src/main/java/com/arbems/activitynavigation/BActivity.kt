package com.arbems.activitynavigation

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class BActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_b)

        val resultIntent = Intent()
        resultIntent.putExtra("text_result_id", "Result text returned")
        setResult(Activity.RESULT_OK, resultIntent)

        finish()
    }
}