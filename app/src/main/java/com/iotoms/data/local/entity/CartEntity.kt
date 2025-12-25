package com.iotoms.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.iotoms.data.enum.TransactionType

/**
 * Created by Fasil on 05/12/2025
 */
@Entity(tableName = "cart")
data class CartEntity(
    @PrimaryKey(autoGenerate = false)
    var transactionNumber: String,
    var transactionType: TransactionType,
    var total: Double,
    var amountDue: Double,
    var amountPaid: Double,
    var tax: Double,
    var discount: Double,
    var customerNumber: String,
    var isUnverifiedReturn: Boolean = false,
    var isOfflineApproved: Boolean = false,
    var isSynced: Boolean = false,
    var createdAt: Long = System.currentTimeMillis()
)