package com.example.aura.non.web.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.aura.non.web.database.ItemDao
import com.example.aura.non.web.database.ItemEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ItemPagingSource(
    private val dao: ItemDao
) : PagingSource<Int, ItemEntity>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ItemEntity> {
        val page = params.key ?: 0

        return try {
            val entities = withContext(Dispatchers.IO) {
                dao.getPagedList(params.loadSize, page * params.loadSize)
            }

            LoadResult.Page(
                data = entities,
                prevKey = if (page == 0) null else page - 1,
                nextKey = if (entities.isEmpty()) null else page + 1
            )

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, ItemEntity>): Int? {
        if (state.anchorPosition == null) return null

        val anchorPage = state.closestPageToPosition(state.anchorPosition!!)

        val anchorPagePrev = anchorPage?.prevKey?.plus(1)
        val anchorPageNext = anchorPage?.nextKey?.minus(1)

        return anchorPagePrev ?: anchorPageNext
    }
}