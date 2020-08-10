package com.arbems.saveandresetuistatus

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_a.*
import kotlinx.android.synthetic.main.activity_main.textView

class ActivityA : AppCompatActivity() {

    private lateinit var viewModel: ValueViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_a)


        viewModel = ViewModelProvider(this).get(ValueViewModel::class.java)

        textView.text = viewModel.value.toString()

        button.setOnClickListener {
            viewModel.value++
            textView.text = viewModel.value.toString()
        }
    }
}
