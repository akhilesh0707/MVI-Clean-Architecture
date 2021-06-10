package com.aqube.mvi.features.articlelist

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import com.aqube.mvi.common.BaseFragment
import com.aqube.mvi.common.PagingLoadStateAdapter
import com.aqube.mvi.databinding.FragmentArticleListBinding
import com.aqube.mvi.domain.model.Article
import com.aqube.mvi.extensions.getMessage
import com.aqube.mvi.extensions.isVisible
import com.aqube.mvi.extensions.makeGone
import com.aqube.mvi.extensions.makeVisible
import com.aqube.mvi.presentation.features.articlelist.ArticleListAction
import com.aqube.mvi.presentation.features.articlelist.ArticleListIntent
import com.aqube.mvi.presentation.features.articlelist.ArticleListState
import com.aqube.mvi.presentation.features.articlelist.ArticleListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class ArticleListFragment : BaseFragment<
        ArticleListIntent,
        ArticleListAction,
        ArticleListState,
        FragmentArticleListBinding, ArticleListViewModel>() {

    override fun getViewBinding(): FragmentArticleListBinding =
        FragmentArticleListBinding.inflate(layoutInflater)

    override val viewModel: ArticleListViewModel by viewModels()

    @Inject
    lateinit var articleListAdapter: ArticleListAdapter

    override fun initUI() {
        initRecyclerView()
        setHasOptionsMenu(true)
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

    override fun render(state: ArticleListState) {
        when (state) {
            is ArticleListState.ResultAllArticles -> {
                submitData(state.data)
            }
            is ArticleListState.ResultSearchArticles -> {
                submitData(state.data)
            }
            is ArticleListState.Exception -> {
                handleErrorMessage(state.callErrors.getMessage(requireContext()))
            }
            else -> {
            }
        }
    }

    private fun submitData(articles: PagingData<Article>) {
        lifecycleScope.launch {
            articleListAdapter.submitData(articles)
        }
    }

    private fun initRecyclerView() = binding.apply {
        recyclerViewArticle.apply {
            adapter = articleListAdapter.withLoadStateFooter(
                footer = PagingLoadStateAdapter { articleListAdapter.retry() }
            )
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }

        buttonRetry.setOnClickListener {
            articleListAdapter.retry()
        }

        // show the loading state for te first load
        articleListAdapter.addLoadStateListener { loadState ->
            handleLoadState(loadState)
        }
    }

    private fun handleLoadState(loadState: CombinedLoadStates) {
        if (loadState.refresh is LoadState.Loading) {
            binding.buttonRetry.makeGone()
            binding.progressBar.makeVisible()
        } else {
            // Hide ProgressBar
            binding.progressBar.makeGone()
            // If we have an error, show a toast
            val errorState = when {
                loadState.append is LoadState.Error -> loadState.append as LoadState.Error
                loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
                loadState.refresh is LoadState.Error -> {
                    binding.buttonRetry.makeVisible()
                    loadState.refresh as LoadState.Error
                }
                else -> null
            }
            errorState?.let {
                handleErrorMessage(it.error.message)
            }
        }
    }

}