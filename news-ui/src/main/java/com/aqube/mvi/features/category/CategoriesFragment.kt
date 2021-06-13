package com.aqube.mvi.features.category

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.aqube.mvi.common.BaseFragment
import com.aqube.mvi.databinding.FragmentCategoriesBinding
import com.aqube.mvi.extensions.getMessage
import com.aqube.mvi.presentation.features.category.CategoriesViewModel
import com.aqube.mvi.presentation.features.category.CategoryAction
import com.aqube.mvi.presentation.features.category.CategoryIntent
import com.aqube.mvi.presentation.features.category.CategoryState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.InternalCoroutinesApi
import javax.inject.Inject

@AndroidEntryPoint
@InternalCoroutinesApi
class CategoriesFragment : BaseFragment<
        CategoryIntent,
        CategoryAction,
        CategoryState,
        FragmentCategoriesBinding, CategoriesViewModel>() {

    override fun getViewBinding(): FragmentCategoriesBinding =
        FragmentCategoriesBinding.inflate(layoutInflater)

    override val viewModel: CategoriesViewModel by viewModels()

    @Inject
    lateinit var categoriesAdapter: CategoriesAdapter

    override fun initUI() {
        initRecyclerView()
    }

    override fun initDATA() {
        dispatchIntent(CategoryIntent.LoadAllCategories)
    }

    override fun initEVENT() {
        categoriesAdapter.setItemClickListener { character ->
            findNavController().navigate(
                CategoriesFragmentDirections.actionCategoriesFragmentToArticleListFragment(
                    character.categoryName
                )
            )
        }
    }

    private fun initRecyclerView() = binding.apply {
        recyclerViewCategories.apply {
            adapter = categoriesAdapter
            layoutManager = GridLayoutManager(requireContext(), 2)
        }
    }

    override fun render(state: CategoryState) {
        when (state) {
            is CategoryState.ResultAllCategories -> {
                categoriesAdapter.list = state.data
            }
            is CategoryState.Exception -> {
                handleErrorMessage(state.callErrors.getMessage(requireContext()))
            }
            else -> {
            }
        }
    }
}