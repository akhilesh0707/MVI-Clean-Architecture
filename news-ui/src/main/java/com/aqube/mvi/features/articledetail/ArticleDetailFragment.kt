package com.aqube.mvi.features.articledetail

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.aqube.mvi.common.BaseFragment
import com.aqube.mvi.common.utils.DateUtil
import com.aqube.mvi.databinding.FragmentArticleDetailBinding
import com.aqube.mvi.domain.model.articles.Article
import com.aqube.mvi.presentation.common.ViewAction
import com.aqube.mvi.presentation.common.ViewIntent
import com.aqube.mvi.presentation.common.ViewState
import com.aqube.mvi.presentation.features.articledetail.ArticleDetailViewModel
import com.bumptech.glide.RequestManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ArticleDetailFragment : BaseFragment<
        ViewIntent,
        ViewAction,
        ViewState,
        FragmentArticleDetailBinding, ArticleDetailViewModel>() {

    @Inject
    lateinit var glide: RequestManager

    private val arguments: ArticleDetailFragmentArgs by navArgs()

    override fun getViewBinding(): FragmentArticleDetailBinding =
        FragmentArticleDetailBinding.inflate(layoutInflater)

    override val viewModel: ArticleDetailViewModel by viewModels()

    override fun initUI() {
        initView(arguments.article)
    }

    override fun initDATA() {
    }

    override fun initEVENT() {
    }

    override fun render(state: ViewState) {
    }

    private fun initView(article: Article) = binding.apply {
        textViewSource.text = article.source.name.uppercase()
        textViewDateTime.text = DateUtil.getDisplayDate(article.publishedAt).uppercase()
        textViewTitle.text = article.title
        textViewDescription.text = article.content
        // Load image
        glide.load(article.urlToImage).into(imageViewArticle)
    }

}