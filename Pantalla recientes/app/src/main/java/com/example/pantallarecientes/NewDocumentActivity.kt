package com.example.pantallarecientes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_new_document.*

class NewDocumentActivity : AppCompatActivity() {

    private val KEY_EXTRA_NEW_DOCUMENT_COUNTER: String = "extra_new_document_counter"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_document)

        var documentCount = intent.getIntExtra(KEY_EXTRA_NEW_DOCUMENT_COUNTER, 0)
        textView.text = documentCount.toString()
    }

    override fun onNewIntent(newIntent: Intent) {
        super.onNewIntent(newIntent)
        /* If FLAG_ACTIVITY_MULTIPLE_TASK has not been used, this Activity
        will be reused. */
        // setDocumentCounterText(R.string.reusing_document_counter)
    }

}
