package com.iotoms.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.iotoms.data.enum.TransactionType
import com.iotoms.data.model.CartDiscount

/**
 * Created by Fasil on 29/11/2025
 */
@Entity(tableName = "cart_items")
data class CartItemEntity(
    @PrimaryKey(autoGenerate = false)
    var id: String,
    var itemId: String,
    var upc: List<String>,
    var name: String,
    var quantity: Double,
    var saleQuantity: Double? = null,
    var price: Double,
    var modifiedPrice: Double? = null,
    var transactionType: TransactionType = TransactionType.SALE,
    var transactionNumber: String,
    var imageUrl: String? = null,
    var cartDiscounts: List<CartDiscount> = emptyList(),
    var discount: Double = cartDiscounts.sumOf { it.discount },
    var tax: Double = 0.0,
    var taxIds: List<Int> = emptyList()
)
