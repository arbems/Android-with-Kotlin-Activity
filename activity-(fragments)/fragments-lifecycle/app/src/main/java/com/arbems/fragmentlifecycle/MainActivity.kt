package com.arbems.fragmentlifecycle

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.arbems.fragmentlifecycle.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var helperFragment: HelperFragmentTransactions

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        helperFragment = HelperFragmentTransactions()

        val binding: ActivityMainBinding = DataBindingUtil.setContentView(
            this, R.layout.activity_main)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        buttonOpenActivity.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }

        viewModel.setTextMsg("Activity_1 - onCreate - ${lifecycle.currentState}")
    }

    override fun onStart() {
        super.onStart()
        viewModel.setTextMsg("Activity_1 - onStart - ${lifecycle.currentState}")
    }

    override fun onResume() {
        super.onResume()
        viewModel.setTextMsg("Activity_1 - onResume - ${lifecycle.currentState}")
    }

    override fun onPause() {
        super.onPause()
        viewModel.setTextMsg("Activity_1 - onPause - ${lifecycle.currentState}")
    }

    override fun onStop() {
        super.onStop()
        viewModel.setTextMsg("Activity_1 - onStop - ${lifecycle.currentState}")
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.setTextMsg("Activity_1 - onDestroy - ${lifecycle.currentState}")
    }

    override fun onRestart() {
        super.onRestart()
        viewModel.setTextMsg("Activity_1 - onRestart - ${lifecycle.currentState}")
    }

    override fun onBackPressed() {
        super.onBackPressed()
        viewModel.setTextMsg("Activity_1 - onBackPressed - ${lifecycle.currentState}")
    }
}