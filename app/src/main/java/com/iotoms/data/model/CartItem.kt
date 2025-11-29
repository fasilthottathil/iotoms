package com.iotoms.data.model

import com.iotoms.data.enum.TransactionType
import kotlinx.serialization.Serializable

/**
 * Created by Fasil on 29/11/2025
 */
@Serializable
data class CartItem(
    var id: String,
    var itemId: String,
    var upc: List<String>,
    var name: String,
    var quantity: Double,
    var saleQuantity: Double,
    var price: Double,
    var modifiedPrice: Double,
    var transactionType: TransactionType = TransactionType.SALE,
    var imageUrl: String? = null,
    var cartDiscounts: List<CartDiscount> = emptyList(),
    var discount: Double = cartDiscounts.sumOf { it.discount }
)
