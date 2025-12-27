package com.iotoms.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.iotoms.data.local.db.AppDatabase
import com.iotoms.data.local.entity.ItemEntity
import com.iotoms.data.mapper.toItemEntity
import com.iotoms.data.model.response.ItemResponse
import com.iotoms.data.model.response.PageResponse
import com.iotoms.data.remote.api.ApiError
import com.iotoms.data.remote.api.apiRequest
import com.iotoms.domain.repository.ItemRepository
import com.iotoms.utils.NetworkError
import com.iotoms.utils.Result
import com.iotoms.utils.constants.ApiUrl
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import kotlinx.coroutines.flow.Flow

/**
 * Created by Fasil on 25/12/2025
 */
class ItemRepositoryImpl(
    private val client: HttpClient,
    private val appDatabase: AppDatabase
) : ItemRepository {
    override suspend fun getAllItemsPaginated(page: Int): Result<List<Boolean>, ApiError> {
        val pageStates = mutableListOf<Boolean>()
        var currentPage = page

        while (true) {

            val result = apiRequest<PageResponse<ItemResponse>> {
                client.get(ApiUrl.ITEMS.plus("?page=$currentPage"))
            }

            when (result) {
                is Result.Success -> {
                    val items = result.data.content?.map { it.toItemEntity() } ?: emptyList()

                    if (items.isEmpty()) {
                        return Result.Success(pageStates)
                    }
                    appDatabase.itemDao().insertItems(items)

                    pageStates += true
                    currentPage++        // move to next page
                }

                is Result.Error -> {
                    // stop only on 404 (no more pages)
                    if (result.error.networkError == NetworkError.NOT_FOUND) {
                        return Result.Success(pageStates)
                    }

                    // for any other error → fail immediately
                    return Result.Error(result.error)
                }
            }
        }
    }

    override fun getPaginateItemsFromLocal(): Flow<PagingData<ItemEntity>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { appDatabase.itemDao().getPaginatedItems() }
        ).flow
    }
}