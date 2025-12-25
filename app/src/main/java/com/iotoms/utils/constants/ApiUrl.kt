package com.iotoms.utils.constants

/**
 * Created by Fasil on 22/11/2025
 */
object ApiUrl {
    const val BASE_URL = "https://beta.iotoms.com"
    const val PAY_MODES = "/api/v1/transaction/paymodes"
    const val CATALOG = "/api/v1/catalog"
    const val ATTRIBUTES = "$CATALOG/attributes"
    const val DEPARTMENTS = "$ATTRIBUTES/departments"
    const val COLORS = "$ATTRIBUTES/colors"
    const val CATEGORIES = "$ATTRIBUTES/categories"
    const val SUBCATEGORIES = "$ATTRIBUTES/subcategories"
    const val BRANDS = "$ATTRIBUTES/brands"
    const val SIZES = "$ATTRIBUTES/sizes"
    const val STYLES = "$ATTRIBUTES/styles"
    const val CUSTOMERS = "/api/v1/crm/customers"
    const val CUSTOMER_ID = "$CUSTOMERS/{id}"
    const val TAXES = "$ATTRIBUTES/taxes"
    const val DISCOUNTS = "$ATTRIBUTES/discounts"
    const val ITEMS = "$CATALOG/items"
}