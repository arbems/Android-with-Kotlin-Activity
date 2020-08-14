package com.arbems.saveandresetuistatus

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button1.setOnClickListener {
            startActivity(Intent(this, ViewModelActivity::class.java))
        }

        button2.setOnClickListener {
            startActivity(Intent(this, InstanceStateActivity::class.java))
        }

        button3.setOnClickListener {
            startActivity(Intent(this, PersistentStorageActivity::class.java))
        }

        button4.setOnClickListener {
            startActivity(Intent(this, SavedStateActivity::class.java))
        }
    }
}
