package com.maciel.murillo.mutalk.core.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<B : ViewBinding> : AppCompatActivity() {

    private var _binding: B? = null

    protected val binding
        get() = requireNotNull(_binding)

    abstract val bindingInflater: (LayoutInflater) -> B

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = bindingInflater.invoke(layoutInflater)
        setContentView(binding.root)

        setUpViews()
        setUpObservers()
        setUpListeners()
        postSetUp()
    }

    @CallSuper
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    protected open fun setUpViews() {}

    protected open fun setUpObservers() {}

    protected open fun setUpListeners() {}

    protected open fun postSetUp() {}
}