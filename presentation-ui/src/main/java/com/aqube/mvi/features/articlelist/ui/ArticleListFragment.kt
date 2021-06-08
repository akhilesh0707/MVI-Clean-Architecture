package com.aqube.mvi.features.articlelist.ui

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.aqube.mvi.common.BaseFragment
import com.aqube.mvi.databinding.FragmentArticleListBinding
import com.aqube.mvi.extensions.getMessage
import com.aqube.mvi.extensions.showLoadingDialog
import com.aqube.mvi.features.articlelist.ArticleListAction
import com.aqube.mvi.features.articlelist.ArticleListIntent
import com.aqube.mvi.features.articlelist.ArticleListState
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ArticleListFragment : BaseFragment<
        ArticleListIntent,
        ArticleListAction,
        ArticleListState,
        FragmentArticleListBinding,
        ArticleListViewModel>() {

    override fun getViewBinding(): FragmentArticleListBinding =
        FragmentArticleListBinding.inflate(layoutInflater)

    override val viewModel: ArticleListViewModel by viewModels()

    @Inject
    lateinit var articleListAdapter: ArticleListAdapter

    override fun initUI() {
        initRecyclerView()
    }

    override fun initDATA() {
        dispatchIntent(ArticleListIntent.LoadAllArticles)
    }

    override fun initEVENT() {
        articleListAdapter.setItemClickListener {
            findNavController().navigate(
                ArticleListFragmentDirections.actionArticleListFragmentToArticleDetailFragment(it)
            )
        }
    }

    private fun initRecyclerView() {
        binding.recyclerViewArticle.apply {
            adapter = articleListAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    override fun render(state: ArticleListState) {
        //showLoadingDialog(state is ArticleListState.Loading)
        when (state) {
            is ArticleListState.ResultAllArticles -> {
                articleListAdapter.list = state.data
            }
            is ArticleListState.ResultSearchArticles -> {
                articleListAdapter.list = state.data
            }
            is ArticleListState.Exception -> {
                handleErrorMessage(state.callErrors.getMessage(requireContext()))
            }
            else -> {}
        }
    }
}