package com.aqube.mvi.features.articlelist

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import com.aqube.mvi.R
import com.aqube.mvi.common.BaseFragment
import com.aqube.mvi.common.PagingLoadStateAdapter
import com.aqube.mvi.databinding.FragmentArticleListBinding
import com.aqube.mvi.domain.model.Article
import com.aqube.mvi.extensions.getMessage
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

    private lateinit var searchView: SearchView

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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_search, menu)

        val searchItem = menu.findItem(R.id.actionSearchArticle)
        searchView = searchItem.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean =
                if (query != null) {
                    searchView.clearFocus()
                    searchForArticles(query)
                    true
                } else {
                    false
                }

            override fun onQueryTextChange(newText: String?): Boolean = false
        })

        searchItem.setOnActionExpandListener(object : MenuItem.OnActionExpandListener {
            override fun onMenuItemActionExpand(item: MenuItem?): Boolean = true

            override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {
                dispatchIntent(ArticleListIntent.LoadAllArticles)
                return true
            }
        })
    }

    private fun searchForArticles(queryText: String) {
        dispatchIntent(ArticleListIntent.SearchArticle(queryText))
        binding.recyclerViewArticle.scrollToPosition(0)
    }
}