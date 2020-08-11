package com.arbems.activitylifecycle

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.arbems.activitylifecycle.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        val binding: ActivityMainBinding = DataBindingUtil.setContentView(
            this, R.layout.activity_main)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        buttonReset.setOnClickListener {
            viewModel.reset()
        }

        buttonOpenNew.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }

        viewModel.setTextMsg("onCreate! - ${lifecycle.currentState}")
    }

    override fun onStart() {
        super.onStart()
        viewModel.setTextMsg("onStart! - ${lifecycle.currentState}")
    }

    override fun onResume() {
        super.onResume()
        viewModel.setTextMsg("onResume! - ${lifecycle.currentState}")
    }

    override fun onPause() {
        super.onPause()
        viewModel.setTextMsg("onPause! - ${lifecycle.currentState}")
    }

    override fun onStop() {
        super.onStop()
        viewModel.setTextMsg("onStop! - ${lifecycle.currentState}")
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.setTextMsg("onDestroy! - ${lifecycle.currentState}")
    }

    override fun onRestart() {
        super.onRestart()
        viewModel.setTextMsg("onRestart! - ${lifecycle.currentState}")
    }

    override fun onBackPressed() {
        super.onBackPressed()
        viewModel.setTextMsg("onBackPressed! - ${lifecycle.currentState}")
    }
}