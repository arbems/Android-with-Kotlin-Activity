package com.arbems.saveandresetuistatus

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_viewmodel.*
import kotlinx.android.synthetic.main.activity_main.textView

class ViewModelActivity : AppCompatActivity() {

    private lateinit var viewModel: MyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_viewmodel)

        viewModel = ViewModelProvider(this).get(MyViewModel::class.java)

        textView.text = viewModel.score.toString()

        button.setOnClickListener {
            viewModel.score++
            textView.text = viewModel.score.toString()
        }
    }
}
