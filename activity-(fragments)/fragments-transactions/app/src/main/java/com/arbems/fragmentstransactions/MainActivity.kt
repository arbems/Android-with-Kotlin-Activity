package com.arbems.fragmentstransactions

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.commit
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Check that the activity is using the layout version with
        // the fragment_container FrameLayout
        if (fragment_container != null) {

            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return;
            }

            // Create a new Fragment to be placed in the activity layout
            val fragment = FirstFragment()

            // In case this activity was started with special instructions from an
            // Intent, pass the Intent's extras to the fragment as arguments
            fragment.arguments = intent.extras

            // Add the fragment to the 'fragment_container' FrameLayout
            supportFragmentManager.beginTransaction()
                    .add(R.id.fragment_container, fragment).commit()
        }
    }

    fun addFragment(view: View) {
        val fragment = FirstFragment()

        fragment.arguments = intent.extras

        // Add the fragment to the 'fragment_container' FrameLayout
        supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, fragment, "fragment_first")
                .addToBackStack(null)
                .commit()
    }

    fun addFragmentKtx(view: View) {
        val fragment = FirstFragment()

        fragment.arguments = intent.extras

        supportFragmentManager.commit {
            addToBackStack("...")
            // setCustomAnimations(
                    // R.anim.enter_anim,
                    // R.anim.exit_anim)
            add(R.id.fragment_container, fragment, "fragment_first")
        }
    }

    fun replaceFragment(view: View) {
        val newFragment = SecondFragment()

        supportFragmentManager.beginTransaction().apply {
            // Replace whatever is in the fragment_container view with this fragment,
            // and add the transaction to the back stack so the user can navigate back
            replace(R.id.fragment_container, newFragment)
            addToBackStack(null)
            commit()
        }
    }

    fun removeFragment(view: View) {
        val fragment = supportFragmentManager.findFragmentByTag("fragment_new")
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