package com.mayur.myimageapp

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.mayur.myimageapp.data.SearchResultItem
import com.mayur.myimageapp.data.imageSearch.ImageRepository

class ImageSearchPagingSource(
    private val imageRepository: ImageRepository,
    private val searchText: String?
) : PagingSource<Int, SearchResultItem>() {

    override fun getRefreshKey(state: PagingState<Int, SearchResultItem>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SearchResultItem> {
        return try {
            if (searchText.isNullOrBlank())
                return LoadResult.Error(Throwable("Failed to get next page"))

            val nextPage = params.key ?: 1
            val searchResults = imageRepository.getSearchedImages(searchText, nextPage)
            Log.i(TAG, "Loading page: $nextPage resultSize: ${searchResults.result?.total}")

            LoadResult.Page(
                data = searchResults?.result?.results ?: listOf(),
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = if (searchResults?.result?.results.isNullOrEmpty()) null else nextPage + 1
            )

        } catch (t: Throwable) {
            LoadResult.Error(Throwable("Failed to get next page"))
        }
    }

    companion object {
        const val TAG = "SearchPagingSource"
    }
}