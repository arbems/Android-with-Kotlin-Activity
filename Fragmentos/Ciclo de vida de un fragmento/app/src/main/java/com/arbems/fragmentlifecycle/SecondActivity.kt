package com.arbems.fragmentlifecycle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.arbems.fragmentlifecycle.databinding.ActivityMainBinding
import com.arbems.fragmentlifecycle.databinding.ActivitySecondBinding
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var helperFragment: HelperFragmentTransactions

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        helperFragment = HelperFragmentTransactions()

        val binding: ActivitySecondBinding = DataBindingUtil.setContentView(
            this, R.layout.activity_second)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        buttonReset.setOnClickListener {
            viewModel.reset()
        }

        buttonAdd.setOnClickListener {
            helperFragment.addFragment(FirstFragment(), "first_fragment", supportFragmentManager, intent, R.id.fragment_container)
        }

        buttonReplace.setOnClickListener {
            helperFragment.replaceFragment(SecondFragment(), "second_fragment", supportFragmentManager, intent, R.id.fragment_container)
        }

        buttonRemove.setOnClickListener {
            helperFragment.removeFragment("first_fragment", supportFragmentManager)
            helperFragment.removeFragment("second_fragment", supportFragmentManager)
        }

        viewModel.setTextMsg("Activity_2 - onCreate - ${lifecycle.currentState}")
    }

    override fun onStart() {
        super.onStart()
        viewModel.setTextMsg("Activity_2 - onStart - ${lifecycle.currentState}")
    }

    override fun onResume() {
        super.onResume()
        viewModel.setTextMsg("Activity_2 - onResume - ${lifecycle.currentState}")
    }

    override fun onPause() {
        super.onPause()
        viewModel.setTextMsg("Activity_2 - onPause - ${lifecycle.currentState}")
    }

    override fun onStop() {
        super.onStop()
        viewModel.setTextMsg("Activity_2 - onStop - ${lifecycle.currentState}")
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.setTextMsg("Activity_2 - onDestroy - ${lifecycle.currentState}")
    }

    override fun onRestart() {
        super.onRestart()
        viewModel.setTextMsg("Activity_2 - onRestart - ${lifecycle.currentState}")
    }

    override fun onBackPressed() {
        super.onBackPressed()
        viewModel.setTextMsg("Activity_2 - onBackPressed - ${lifecycle.currentState}")
    }
}