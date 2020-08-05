package com.arbems.fragmentsbasic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View

class MainActivity : AppCompatActivity() {
    private val fragmentManager = supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    /**
     * add a fragment
     */
    fun addDynamicFragment(view: View) {
        val dynamicFragment = DynamicFragment()
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.fragment_container, dynamicFragment,"fragment_dynamic")
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    /**
     * replace a fragment by other
     */
    fun replaceDynamicFragment(view: View) {
        val staticFragment = StaticFragment()
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, staticFragment, "fragment_static")
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    /**
     * remove a fragment
     */
    fun removeDynamicFragment(view: View) {
        Log.i("", R.layout.fragment_static.toString())
        val fragment = supportFragmentManager.findFragmentByTag("fragment_dynamic")
        if(fragment != null) {
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.remove(fragment)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }
        else {
            Log.i("removeDynamicFragment", "fragment is null")
        }
    }
}