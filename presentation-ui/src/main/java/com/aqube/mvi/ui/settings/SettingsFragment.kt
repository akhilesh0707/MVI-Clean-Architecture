package com.aqube.mvi.ui.settings

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.aqube.mvi.base.BaseFragment
import com.aqube.mvi.base.BaseViewModel
import com.aqube.mvi.databinding.FragmentSettingsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingsFragment : BaseFragment<FragmentSettingsBinding, BaseViewModel>() {

    override fun getViewBinding(): FragmentSettingsBinding =
        FragmentSettingsBinding.inflate(layoutInflater)

    override val viewModel: SettingsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}