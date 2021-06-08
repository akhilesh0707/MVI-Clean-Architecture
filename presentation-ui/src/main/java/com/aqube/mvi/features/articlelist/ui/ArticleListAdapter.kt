package com.aqube.mvi.features.articlelist.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.aqube.mvi.common.BaseAdapter
import com.aqube.mvi.databinding.ItemArticleListBinding
import com.aqube.mvi.domain.model.Article
import com.aqube.mvi.common.utils.DateUtil
import com.bumptech.glide.RequestManager
import javax.inject.Inject

class ArticleListAdapter @Inject constructor(
    private val glide: RequestManager
) : BaseAdapter<Article>() {

    private val diffCallback = object : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }

    override val differ = AsyncListDiffer(this, diffCallback)

    override fun getViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding =
            ItemArticleListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ArticleViewHolder(binding)
    }

    inner class ArticleViewHolder(private val binding: ItemArticleListBinding) :
        RecyclerView.ViewHolder(binding.root), Binder<Article> {
        override fun bind(item: Article) {
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