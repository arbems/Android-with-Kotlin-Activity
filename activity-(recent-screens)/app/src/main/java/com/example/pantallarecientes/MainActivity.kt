package com.example.pantallarecientes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var documentCounter: Int = 0
    private val KEY_EXTRA_NEW_DOCUMENT_COUNTER: String = "extra_new_document_counter"
    private val useMultipleTasks: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            createNewDocument()
        }

        button2.setOnClickListener {

        }
    }

    fun createNewDocument() {
        val newDocumentIntent = newDocumentIntent()
        if (useMultipleTasks) {
            newDocumentIntent.addFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK)
        }
        startActivity(newDocumentIntent)
    }

    private fun newDocumentIntent(): Intent =
        Intent(this, NewDocumentActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT or
                    android.content.Intent.FLAG_ACTIVITY_RETAIN_IN_RECENTS)
            putExtra(KEY_EXTRA_NEW_DOCUMENT_COUNTER, documentCounter++)
        }


}
