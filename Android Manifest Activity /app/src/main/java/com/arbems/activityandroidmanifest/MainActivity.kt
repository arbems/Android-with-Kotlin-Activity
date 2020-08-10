package com.arbems.activityandroidmanifest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button1.setOnClickListener {
            /**
             * Explicit call to activity
             */
            val intent = Intent(this, AActivity::class.java)
            startActivity(intent)
        }

        button2.setOnClickListener {
            /**
             * Implicit call to activity
             */
            val sendIntent = Intent().apply {
                action = Intent.ACTION_SEND
                type = "text/plain"
                putExtra(Intent.EXTRA_TEXT, "textMessage")
            }
            startActivity(sendIntent)
        }
    }
}