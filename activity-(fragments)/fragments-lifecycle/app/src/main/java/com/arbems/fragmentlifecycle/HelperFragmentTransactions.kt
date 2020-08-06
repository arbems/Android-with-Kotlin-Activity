package com.arbems.fragmentlifecycle

import android.content.Intent
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

class HelperFragmentTransactions {

    fun addFragment(
        fragment: Fragment,
        tag: String,
        supportFragmentManager: FragmentManager,
        intent: Intent,
        container: Int
    ) {
        fragment.arguments = intent.extras

        // Add the fragment to the 'fragment_container' FrameLayout
        supportFragmentManager.beginTransaction()
            .add(container, fragment, tag)
            .addToBackStack(null)
            .commit()
    }

    fun replaceFragment(
        newFragment: Fragment,
        tag: String,
        supportFragmentManager: FragmentManager,
        intent: Intent,
        container: Int
    ) {
        newFragment.arguments = intent.extras

        // Add the fragment to the 'fragment_container' FrameLayout
        supportFragmentManager.beginTransaction()
            .replace(container, newFragment, tag)
            .addToBackStack(null)
            .commit()
    }

    fun removeFragment(
        tag: String,
        supportFragmentManager: FragmentManager
    ) {
        val fragment = supportFragmentManager.findFragmentByTag(tag)
        if (fragment != null) {
            // Remove the fragment 'fragment_new'
            supportFragmentManager.beginTransaction().apply {
                remove(fragment)
                addToBackStack(null)
                commit()
            }
        }
    }
}