package com.iotoms.data.repository

import com.iotoms.data.enum.SyncStatus
import com.iotoms.domain.repository.AttributeRepository
import com.iotoms.domain.repository.BusinessRepository
import com.iotoms.domain.repository.CustomerRepository
import com.iotoms.domain.repository.DataSyncRepository
import com.iotoms.domain.repository.ItemRepository
import com.iotoms.domain.repository.PayModeRepository
import com.iotoms.domain.repository.TaxRepository
import com.iotoms.domain.repository.UserRepository
import com.iotoms.utils.Result
import kotlinx.coroutines.flow.flow

/**
 * Created by Fasil on 23/11/2025
 */
class DataSyncRepositoryImpl(
    private val itemRepository: ItemRepository,
    private val customerRepository: CustomerRepository,
    private val attributeRepository: AttributeRepository,
    private val payModeRepository: PayModeRepository,
    private val taxRepository: TaxRepository,
    private val userRepository: UserRepository,
    private val businessRepository: BusinessRepository
): DataSyncRepository {
    override suspend fun doSync() = flow {
        emit(Triple(SyncStatus.IN_PROGRESS, 0.1f,itemRepository.getAllItemsPaginated(0) is Result.Success))
        emit(Triple(SyncStatus.IN_PROGRESS, 0.2f,customerRepository.getCustomers(0) is Result.Success))
        emit(Triple(SyncStatus.IN_PROGRESS, 0.3f,attributeRepository.getSizes() is Result.Success))
        emit(Triple(SyncStatus.IN_PROGRESS, 0.32f,businessRepository.getAllStores() is Result.Success))
        emit(Triple(SyncStatus.IN_PROGRESS, 0.39f,businessRepository.getAllVenues() is Result.Success))
        emit(Triple(SyncStatus.IN_PROGRESS, 0.4f,businessRepository.getAllRegisters() is Result.Success))
        emit(Triple(SyncStatus.IN_PROGRESS, 0.42f,businessRepository.getAllBranding() is Result.Success))
        emit(Triple(SyncStatus.IN_PROGRESS, 0.5f,attributeRepository.getBrands() is Result.Success))
        emit(Triple(SyncStatus.IN_PROGRESS,0.59f ,attributeRepository.getColors() is Result.Success))
        emit(Triple(SyncStatus.IN_PROGRESS, 0.6f,attributeRepository.getStyles() is Result.Success))
        emit(Triple(SyncStatus.IN_PROGRESS, 0.8f,attributeRepository.getCategories() is Result.Success))
        emit(Triple(SyncStatus.IN_PROGRESS, 0.81f,attributeRepository.getSubCategories() is Result.Success))
        emit(Triple(SyncStatus.IN_PROGRESS, 0.83f,attributeRepository.getDepartments() is Result.Success))
        emit(Triple(SyncStatus.IN_PROGRESS, 0.84f,payModeRepository.getPayModes() is Result.Success))
        emit(Triple(SyncStatus.IN_PROGRESS, 0.9f,taxRepository.getAllTaxes() is Result.Success))
        emit(Triple(SyncStatus.IN_PROGRESS, 0.91f,userRepository.getAllUsers() is Result.Success))
        emit(Triple(SyncStatus.COMPLETED, 10f,true))
    }
}