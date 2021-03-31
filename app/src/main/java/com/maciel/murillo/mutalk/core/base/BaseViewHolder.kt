package com.maciel.murillo.mutalk.core.base

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseViewHolder<B : ViewBinding, in T>(
        protected val binding: B
) : RecyclerView.ViewHolder(binding.root) {

    protected val context: Context by lazy { binding.root.context }

    open fun bind(item: T) {}
}