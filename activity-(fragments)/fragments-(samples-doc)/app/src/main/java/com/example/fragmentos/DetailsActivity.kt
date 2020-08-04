package com.example.fragmentos

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // If the screen is now in landscape mode, we can show the
            // dialog in-line with the list so we don't need this activity.
            finish()
            return
        }

        if (savedInstanceState == null) {
            // During initial setup, plug in the details fragment.
            val details = DetailsFragment().apply {
                arguments = intent.extras
            }
            supportFragmentManager.beginTransaction()
                .add(android.R.id.content, details)
                .commit()
        }
    }
}
