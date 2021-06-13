package com.aqube.mvi.features.category

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.aqube.mvi.common.BaseAdapter
import com.aqube.mvi.databinding.ItemCategoriesBinding
import com.aqube.mvi.domain.model.categories.Category
import com.aqube.mvi.extensions.getResource
import com.bumptech.glide.RequestManager
import javax.inject.Inject

class CategoriesAdapter @Inject constructor(
    private val glide: RequestManager
) : BaseAdapter<Category>() {

    private val diffCallback = object : DiffUtil.ItemCallback<Category>() {
        override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    override val differ = AsyncListDiffer(this, diffCallback)

    override fun getViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding =
            ItemCategoriesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CharacterViewHolder(binding)
    }

    inner class CharacterViewHolder(private val binding: ItemCategoriesBinding) :
        RecyclerView.ViewHolder(binding.root), Binder<Category> {
        override fun bind(item: Category) {
            binding.apply {
                textViewCategory.text = item.categoryName
                glide.load(imageViewCategory.context.getResource(item.categoryImgUrl))
                    .into(imageViewCategory)
                root.setOnClickListener {
                    onItemClickListener?.let { itemClick ->
                        itemClick(item)
                    }
                }
            }
        }
    }
}