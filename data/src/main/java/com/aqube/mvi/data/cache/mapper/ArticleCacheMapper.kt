package com.aqube.mvi.data.cache.mapper

import com.aqube.mvi.data.cache.models.ArticleEntity
import com.aqube.mvi.domain.model.articles.Article
import javax.inject.Inject

class ArticleCacheMapper @Inject constructor(
    private val sourceCacheMapper: SourceCacheMapper
) : CacheMapper<ArticleEntity, Article> {
    override fun mapFromCached(type: ArticleEntity): Article {
        return Article(
            title = type.title,
            source = sourceCacheMapper.mapFromCached(type.source),
            author = type.author,
            content = type.content,
            description = type.description,
            publishedAt = type.publishedAt,
            url = type.url,
            urlToImage = type.urlToImage
        )
    }

    override fun mapToCached(type: Article): ArticleEntity {
        return ArticleEntity(
            title = type.title,
            source = sourceCacheMapper.mapToCached(type.source),
            author = type.author,
            content = type.content,
            description = type.description,
            publishedAt = type.publishedAt,
            url = type.url,
            urlToImage = type.urlToImage,
            false
        )
    }
}