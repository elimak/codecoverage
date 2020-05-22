package com.elimak.codecoverage.ui.landing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.elimak.codecoverage.App
import com.elimak.codecoverage.databinding.LandingPageBinding

class LandingPage : Fragment() {

    private lateinit var viewModel: LandingViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.injector.inject(this)
        viewModel = ViewModelProvider(this).get(LandingViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return LandingPageBinding.inflate(inflater, container, false).also {
            it.setLifecycleOwner(this)
            it.viewmodel = viewModel
            it.executePendingBindings()
        }.root
    }
}
