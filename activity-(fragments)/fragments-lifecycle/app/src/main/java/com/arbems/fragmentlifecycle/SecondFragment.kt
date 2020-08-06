package com.arbems.fragmentlifecycle

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels

class SecondFragment : Fragment() {
    private val viewModel: MainViewModel by activityViewModels()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel.setTextMsg("Fragm_2 - onAttach - ${lifecycle.currentState}")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.setTextMsg("Fragm_2 - onCreate - ${lifecycle.currentState}")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel.setTextMsg("Fragm_2 - onCreateView - ${lifecycle.currentState}")
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.setTextMsg("Fragm_2 - onViewCreated - ${lifecycle.currentState}")
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.setTextMsg("Fragm_2 - onActivityCreated - ${lifecycle.currentState}")
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        viewModel.setTextMsg("Fragm_2 - onViewStateRestored - ${lifecycle.currentState}")
    }

    override fun onStart() {
        super.onStart()
        viewModel.setTextMsg("Fragm_2 - onStart - ${lifecycle.currentState}")
    }

    override fun onResume() {
        super.onResume()
        viewModel.setTextMsg("Fragm_2 - onResume - ${lifecycle.currentState}")
    }

    override fun onPause() {
        super.onPause()
        viewModel.setTextMsg("Fragm_2 - onPause - ${lifecycle.currentState}")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        viewModel.setTextMsg("Fragm_2 - onSaveInstanceState - ${lifecycle.currentState}")
    }

    override fun onStop() {
        super.onStop()
        viewModel.setTextMsg("Fragm_2 - onStop - ${lifecycle.currentState}")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.setTextMsg("Fragm_2 - onDestroyView - ${lifecycle.currentState}")
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.setTextMsg("Fragm_2 - onDestroy - ${lifecycle.currentState}")
    }

    override fun onDetach() {
        super.onDetach()
        viewModel.setTextMsg("Fragm_2 - onDetach - ${lifecycle.currentState}")
    }
}