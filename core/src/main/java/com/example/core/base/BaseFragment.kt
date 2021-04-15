package com.example.core.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.example.core.helper.EventObserver

abstract class BaseFragment<B : ViewBinding> : Fragment() {

    private var _binding: B? = null

    protected val binding
        get() = requireNotNull(_binding)

    abstract val viewModel: BaseViewModel

    abstract val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> B

    final override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = bindingInflater.invoke(inflater, container, false)
        return binding.root
    }

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpViews()
        setUpObservers()
        setUpListeners()
        postSetUp()
    }

    @CallSuper
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    protected open fun setUpViews() {}

    protected open fun setUpObservers() {
        viewModel.connectivityError.observe(viewLifecycleOwner, EventObserver {
            onConnectivityError()
        })
    }

    protected open fun setUpListeners() {}

    protected open fun postSetUp() {}

    protected open fun onConnectivityError() {}
}