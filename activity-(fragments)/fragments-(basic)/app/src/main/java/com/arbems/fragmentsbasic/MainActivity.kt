package com.arbems.fragmentsbasic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    private val fragmentManager = supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun addDynamicFragment(view: View) {
        /** Dynamic Fragment */
        val dynamicFragment = DynamicFragment()
        /* Display First Fragment initially */
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.dynamicFragment, dynamicFragment)
        fragmentTransaction.commit()
    }
}