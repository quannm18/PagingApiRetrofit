package com.example.clonepaging.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.clonepaging.network.RetrofitInstance
import com.example.clonepaging.network.RetrofitService
import com.example.clonepaging.network.RickAndMortyList
import com.example.clonepaging.repository.CharacterPagingSource
import kotlinx.coroutines.flow.Flow

private const val ITEMS_PER_PAGE =20
class CharacterViewModel : ViewModel() {
    var retrofitService: RetrofitService

    init {
        retrofitService = RetrofitInstance.getRetroInstance().create(RetrofitService::class.java)
    }

    fun getListData(): Flow<PagingData<RickAndMortyList.CharacterData>> {
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE, enablePlaceholders = false),
            pagingSourceFactory = {
                CharacterPagingSource(retrofitService)
            }
        ).flow.cachedIn(viewModelScope)
    }
}