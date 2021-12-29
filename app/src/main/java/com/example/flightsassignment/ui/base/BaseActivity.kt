package com.example.flightsassignment.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<BINDING : ViewBinding?, VM : BaseViewModel?> :
    AppCompatActivity() {
    protected var viewModel: VM? = null
    protected var binding: BINDING? = null
    @NonNull
    protected abstract fun createViewModel(): VM
    @NonNull
    protected abstract fun createViewBinding(layoutInflater: LayoutInflater?): BINDING
    protected override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = createViewBinding(LayoutInflater.from(this))
        setContentView(binding?.getRoot())
        viewModel = createViewModel()
    }
}