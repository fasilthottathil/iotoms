package com.iotoms.data.repository

import com.iotoms.data.local.db.AppDatabase
import com.iotoms.data.local.entity.QuickPickEntity
import com.iotoms.data.local.pref.AppPreference
import com.iotoms.data.mapper.toQuickPickEntity
import com.iotoms.data.model.response.QuickPickResponse
import com.iotoms.data.remote.api.ApiError
import com.iotoms.data.remote.api.apiRequest
import com.iotoms.domain.repository.QuickPickRepository
import com.iotoms.utils.Result
import com.iotoms.utils.constants.ApiUrl
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import kotlinx.coroutines.flow.Flow

/**
 * Created by Fasil on 28/12/2025
 */
class QuickPickRepositoryImpl(
    private val client: HttpClient,
    private val appDatabase: AppDatabase,
    private val appPreference: AppPreference
) : QuickPickRepository {
    override suspend fun getAllQuickPicks(): Result<QuickPickResponse, ApiError> {
        val store = requireNotNull(
            appDatabase.registerDao().getRegisterById(appPreference.getRegisterId())
        ).store
        return apiRequest<QuickPickResponse> {
            client.get(ApiUrl.QUICKPICKS + "?registerId=${appPreference.getRegisterId()}&storeId=${store?.id}&venueId=${store?.venue?.id}")
        }.also {
            if (it is Result.Success) {
                appDatabase.quickPickDao().clearQuickPicks()
                val quickPickEntities = it.data.pages?.mapNotNull { item ->
                    item?.toQuickPickEntity()
                } ?: emptyList()
                appDatabase.quickPickDao().insertQuickPicks(quickPickEntities)
            }
        }
    }

    override fun getQuickPicksFromDb(): Flow<List<QuickPickEntity>> {
        return appDatabase.quickPickDao().getAllQuickPicks()
    }
}