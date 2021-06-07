package com.aqube.mvi.ui.articlelist

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.aqube.mvi.base.BaseFragment
import com.aqube.mvi.base.BaseViewModel
import com.aqube.mvi.databinding.FragmentArticleListBinding
import com.aqube.mvi.extensions.observe
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ArticleListFragment : BaseFragment<FragmentArticleListBinding, BaseViewModel>() {

    override fun getViewBinding(): FragmentArticleListBinding =
        FragmentArticleListBinding.inflate(layoutInflater)

    override val viewModel: ArticleListViewModel by viewModels()

    @Inject
    lateinit var articleListAdapter: ArticleListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getTopHeadings()
        observe(viewModel.getArticles(), ::onViewStateChange)
        initRecyclerView()
        initRecyclerView()
    }

    private fun initRecyclerView() {
        binding.recyclerViewArticle.apply {
            adapter = articleListAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        articleListAdapter.setItemClickListener { article ->
            findNavController().navigate(
                ArticleListFragmentDirections.actionArticleListFragmentToArticleDetailFragment(0)
            )
        }
    }

    private fun onViewStateChange(event: ArticleUIModel) {
        if (event.isRedelivered) return
        when (event) {
            is ArticleUIModel.Error -> handleErrorMessage(event.error)
            is ArticleUIModel.Loading -> handleLoading(true)
            is ArticleUIModel.Success -> {
                event.data.let {
                    articleListAdapter.list = it
                }
            }
        }
    }
}