package com.arbems.activitynavigation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_a.*

class AActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_a)

        /**
         * Get text from intent
         */
        textView.text = intent?.extras?.get("text_id")?.toString()

        button.setOnClickListener {
            /**
             * Create explicit intent and start activity for result
             */
            val intent: Intent = Intent(this, BActivity::class.java)
            startActivityForResult(intent, CODE_REQUEST)
        }
    }

    /**
     * Callback where result is obtained
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {
        super.onActivityResult(requestCode, resultCode, intent)

        when (requestCode) {
            Companion.CODE_REQUEST ->
                if (resultCode == RESULT_OK) {
                    textView.text = intent?.extras?.get("text_result_id") as String
                }
        }
    }

    /**
     * The request code
     */
    companion object {
        const val CODE_REQUEST = 1
    }

}