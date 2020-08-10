package com.arbems.fragmentlifecycle

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels

class FirstFragment : Fragment() {

    private val viewModel: MainViewModel by activityViewModels()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel.setTextMsg("Fragm_1 - onAttach - ${lifecycle.currentState}")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.setTextMsg("Fragm_1 - onCreate - ${lifecycle.currentState}")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel.setTextMsg("Fragm_1 - onCreateView - ${lifecycle.currentState}")
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.setTextMsg("Fragm_1 - onViewCreated - ${lifecycle.currentState}")
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.setTextMsg("Fragm_1 - onActivityCreated - ${lifecycle.currentState}")
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        viewModel.setTextMsg("Fragm_1 - onViewStateRestored - ${lifecycle.currentState}")
    }

    override fun onStart() {
        super.onStart()
        viewModel.setTextMsg("Fragm_1 - onStart - ${lifecycle.currentState}")
    }

    override fun onResume() {
        super.onResume()
        viewModel.setTextMsg("Fragm_1 - onResume - ${lifecycle.currentState}")
    }

    override fun onPause() {
        super.onPause()
        viewModel.setTextMsg("Fragm_1 - onPause - ${lifecycle.currentState}")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        viewModel.setTextMsg("Fragm_1 - onSaveInstanceState - ${lifecycle.currentState}")
    }

    override fun onStop() {
        super.onStop()
        viewModel.setTextMsg("Fragm_1 - onStop - ${lifecycle.currentState}")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.setTextMsg("Fragm_1 - onDestroyView - ${lifecycle.currentState}")
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.setTextMsg("Fragm_1 - onDestroy - ${lifecycle.currentState}")
        Log.i("Fragment", "onDestroy")
    }

    override fun onDetach() {
        super.onDetach()
        viewModel.setTextMsg("Fragm_1 - onDetach - ${lifecycle.currentState}")
        Log.i("Fragment", "onDetach")
    }
}