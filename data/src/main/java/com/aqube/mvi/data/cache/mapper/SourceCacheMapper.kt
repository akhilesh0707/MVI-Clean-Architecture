package com.aqube.mvi.data.cache.mapper

import com.aqube.mvi.data.cache.models.SourceEntity
import com.aqube.mvi.domain.model.articles.Source
import javax.inject.Inject

class SourceCacheMapper @Inject constructor() : CacheMapper<SourceEntity, Source> {
    override fun mapFromCached(type: SourceEntity): Source {
        return Source(name = type.name, id = type.id)
    }

    override fun mapToCached(type: Source): SourceEntity {
        return SourceEntity(name = type.name, id = type.id)
    }
}