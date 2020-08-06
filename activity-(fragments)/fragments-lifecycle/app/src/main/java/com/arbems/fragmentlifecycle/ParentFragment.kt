package com.arbems.fragmentlifecycle

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class ParentFragment : Fragment() {

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.i("ParentFragment", "onAttach - ${lifecycle.currentState}")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("ParentFragment", "onCreate - ${lifecycle.currentState}")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.i("ParentFragment", "onCreateView - ${lifecycle.currentState}")
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_parent, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.i("ParentFragment", "onViewCreated - ${lifecycle.currentState}")
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.i("ParentFragment", "onActivityCreated - ${lifecycle.currentState}")
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        Log.i("ParentFragment", "onViewStateRestored - ${lifecycle.currentState}")
    }

    override fun onStart() {
        super.onStart()
        Log.i("ParentFragment", "onStart - ${lifecycle.currentState}")
    }

    override fun onResume() {
        super.onResume()
        Log.i("ParentFragment", "onResume - ${lifecycle.currentState}")
    }

    override fun onPause() {
        super.onPause()
        Log.i("ParentFragment", "onPause - ${lifecycle.currentState}")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.i("ParentFragment", "onSaveInstanceState - ${lifecycle.currentState}")
    }

    override fun onStop() {
        super.onStop()
        Log.i("ParentFragment", "onStop - ${lifecycle.currentState}")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.i("ParentFragment", "onDestroyView - ${lifecycle.currentState}")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("ParentFragment", "onDestroy")
    }

    override fun onDetach() {
        super.onDetach()
        Log.i("ParentFragment", "onDetach")
    }
}