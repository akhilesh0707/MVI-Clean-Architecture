package com.aqube.mvi.ui.category

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.aqube.mvi.base.BaseFragment
import com.aqube.mvi.base.BaseViewModel
import com.aqube.mvi.databinding.FragmentCategoriesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoriesFragment : BaseFragment<FragmentCategoriesBinding, BaseViewModel>() {

    override fun getViewBinding(): FragmentCategoriesBinding =
        FragmentCategoriesBinding.inflate(layoutInflater)

    override val viewModel: CategoriesViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}