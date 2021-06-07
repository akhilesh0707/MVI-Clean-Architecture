package com.aqube.mvi.ui.articledetail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.aqube.mvi.base.BaseFragment
import com.aqube.mvi.base.BaseViewModel
import com.aqube.mvi.databinding.FragmentArticleDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArticleDetailFragment : BaseFragment<FragmentArticleDetailBinding, BaseViewModel>() {

    override fun getViewBinding(): FragmentArticleDetailBinding =
        FragmentArticleDetailBinding.inflate(layoutInflater)

    override val viewModel: ArticleDetailViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}