package com.iotoms.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.iotoms.data.local.entity.CustomerEntity

/**
 * Created by Fasil on 26/12/2025
 */
@Dao
interface CustomerDao {
    @Upsert
    suspend fun insertCustomers(customers: List<CustomerEntity>)

    @Query("DELETE FROM customers")
    suspend fun deleteAllCustomers()

    @Query("SELECT * FROM customers")
    suspend fun getAllCustomers(): List<CustomerEntity>

    @Query("SELECT * FROM customers WHERE customerNumber = :customerId")
    suspend fun getCustomerById(customerId: String): CustomerEntity?

    @Query("DELETE FROM customers WHERE customerNumber = :customerId")
    suspend fun deleteCustomerById(customerId: String)

}