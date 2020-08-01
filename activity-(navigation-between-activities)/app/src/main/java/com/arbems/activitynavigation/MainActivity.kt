package com.arbems.activitynavigation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            /**
             * Create explicit intent and start activity
             */
            val intent: Intent = Intent(this, AActivity::class.java)
            intent.putExtra("text_id", "My text to send")
            startActivity(intent)
        }
    }
}