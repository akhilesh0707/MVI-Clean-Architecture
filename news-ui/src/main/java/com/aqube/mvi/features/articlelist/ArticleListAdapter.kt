package com.aqube.mvi.features.articlelist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.aqube.mvi.common.utils.DateUtil
import com.aqube.mvi.databinding.ItemArticleListBinding
import com.aqube.mvi.domain.model.articles.Article
import com.bumptech.glide.RequestManager
import javax.inject.Inject

class ArticleListAdapter @Inject constructor(
    private val glide: RequestManager
) : PagingDataAdapter<Article, ArticleListAdapter.ArticleViewHolder>(COMPARATOR) {

    var onItemClickListener: ((Article) -> Unit)? = null

    fun setItemClickListener(listener: (Article) -> Unit) {
        onItemClickListener = listener
    }

    object COMPARATOR : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val binding =
            ItemArticleListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ArticleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = getItem(position)
        if (article != null) {
            holder.bind(article)
        }
    }

    inner class ArticleViewHolder(private val binding: ItemArticleListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Article) {
            binding.apply {
                textViewTitle.text = item.title
                glide.load(item.urlToImage).into(imageViewArticle)
                root.setOnClickListener {
                    onItemClickListener?.let { itemClick ->
                        itemClick(item)
                    }
                }
                textViewTime.text = DateUtil.getTimeAgo(item.publishedAt)
            }
        }
    }
}