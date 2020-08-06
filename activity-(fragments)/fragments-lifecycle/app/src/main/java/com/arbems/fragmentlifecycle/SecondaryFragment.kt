package com.arbems.fragmentlifecycle

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class SecondaryFragment : Fragment() {

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.i("SecondaryFragment", "onAttach")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("SecondaryFragment", "onCreate - ${lifecycle.currentState}")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.i("SecondaryFragment", "onCreateView - ${lifecycle.currentState}")
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_secondary, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.i("SecondaryFragment", "onViewCreated - ${lifecycle.currentState}")
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.i("SecondaryFragment", "onActivityCreated - ${lifecycle.currentState}")
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        Log.i("SecondaryFragment", "onViewStateRestored - ${lifecycle.currentState}")
    }

    override fun onStart() {
        super.onStart()
        Log.i("SecondaryFragment", "onStart - ${lifecycle.currentState}")
    }

    override fun onResume() {
        super.onResume()
        Log.i("SecondaryFragment", "onResume - ${lifecycle.currentState}")
    }

    override fun onPause() {
        super.onPause()
        Log.i("SecondaryFragment", "onPause - ${lifecycle.currentState}")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.i("SecondaryFragment", "onSaveInstanceState - ${lifecycle.currentState}")
    }

    override fun onStop() {
        super.onStop()
        Log.i("SecondaryFragment", "onStop - ${lifecycle.currentState}")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.i("SecondaryFragment", "onDestroyView - ${lifecycle.currentState}")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("SecondaryFragment", "onDestroy - ${lifecycle.currentState}")
    }

    override fun onDetach() {
        super.onDetach()
        Log.i("SecondaryFragment", "onDetach - ${lifecycle.currentState}")
    }
}