package com.example.clonepaging.repository

import android.net.Uri
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.clonepaging.network.RetrofitService
import com.example.clonepaging.network.RickAndMortyList
import com.example.clonepaging.network.RickAndMortyList.*
import java.io.IOException
import kotlin.math.max

class CharacterPagingSource (val mApi : RetrofitService) : PagingSource<Int, CharacterData>() {
    override fun getRefreshKey(state: PagingState<Int, CharacterData>): Int? {
        var anchorPos = state.anchorPosition?:return null
        val mItem = state.closestItemToPosition(anchorPos)?:return null

        return ensureValidKey(key = mItem.name?.length ?: (0 - 10))
    }

    companion object{
        private const val FIRST_PAGE_INDEX = 1
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterData> {
        return try {
            val nextPage = params.key?:FIRST_PAGE_INDEX
            val response = mApi.getDataFromAPI(nextPage)
            var nextPageNumber: Int? =null
            if (response?.info?.next!=null){
                val uri = Uri.parse(response?.info?.next)
                val nextPageQuery = uri.getQueryParameter("page")
                nextPageNumber = nextPageQuery?.toInt()
            }
            LoadResult.Page(data = response.results,
            prevKey = null,
            nextKey = nextPageNumber)
        }catch (e:IOException){
            LoadResult.Error(e)
        }catch (e: Exception){
            LoadResult.Error(e)
        }
    }


    private fun ensureValidKey(key: Int) = max(FIRST_PAGE_INDEX, key)
}