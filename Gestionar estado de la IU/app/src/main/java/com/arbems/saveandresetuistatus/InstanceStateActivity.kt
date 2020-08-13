package com.arbems.saveandresetuistatus

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_instace_state.button
import kotlinx.android.synthetic.main.activity_instace_state.textView

class InstanceStateActivity : AppCompatActivity() {

    private val key = "textView"
    private var score: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_instace_state)

        /*
        // Restore values here or in onRestoreInstanceState()
        if (savedInstanceState != null) {
            with(savedInstanceState) {
                // Restore the value of members from the saved state
                value = getInt(key)
                textView.text = value.toString()
            }
        } else {
            // Initializes members probably default values for a new instance
        } */

        button.setOnClickListener {
            score++
            textView.text = score.toString()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState?.run {
            putInt(key, score)
        }

        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)

        savedInstanceState?.run {
            score = getInt(key)
            textView.text = score.toString()
        }
    }
}
