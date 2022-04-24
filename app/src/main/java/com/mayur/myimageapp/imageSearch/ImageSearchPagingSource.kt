package com.mayur.myimageapp.imageSearch

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.mayur.myimageapp.data.imageSearch.ImageRepository
import com.mayur.myimageapp.data.imageSearch.SearchResultItem

class ImageSearchPagingSource(
    private val imageRepository: ImageRepository,
    private val searchText: String?
) : PagingSource<Int, SearchResultItem>() {

    override fun getRefreshKey(state: PagingState<Int, SearchResultItem>): Int? {
        // TODO fix infinite scrolling
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SearchResultItem> {
        Log.i(TAG, "Loading load(params called")
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
        const val TAG = "ImageSearchPagingSource"
    }
}