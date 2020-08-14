package com.arbems.saveandresetuistatus

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_saved_state.*

class SavedStateActivity : AppCompatActivity() {
    private val viewModel: SavedStateViewModel by lazy {
        SavedStateViewModel.getViewModel(application, this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_saved_state)

        textView.text = viewModel.score.value.toString()

        button.setOnClickListener {
            var score = viewModel.score.value?.toInt()
            score = score?.plus(1)

            viewModel.setScore(score)

            textView.text = viewModel.score.value.toString()
        }
    }
}