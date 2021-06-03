package com.aqube.mvi.ui.articlelist

import androidx.fragment.app.viewModels
import com.aqube.mvi.base.BaseFragment
import com.aqube.mvi.base.BaseViewModel
import com.aqube.mvi.databinding.FragmentArticleListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArticleListFragment : BaseFragment<FragmentArticleListBinding, BaseViewModel>() {

    override fun getViewBinding(): FragmentArticleListBinding =
        FragmentArticleListBinding.inflate(layoutInflater)

    override val viewModel: ArticleListViewModel by viewModels()

}