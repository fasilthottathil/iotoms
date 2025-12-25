package com.iotoms.data.model.response

import com.google.gson.annotations.SerializedName

/**
 * Created by Fasil on 25/12/2025
 */
data class PageResponse<T>(
    @field:SerializedName("totalElements")
    val totalElements: Long? = null,

    @field:SerializedName("totalPages")
    val totalPages: Int? = null,

    @field:SerializedName("pageable")
    val pageable: Pageable? = null,

    @field:SerializedName("numberOfElements")
    val numberOfElements: Int? = null,

    @field:SerializedName("size")
    val size: Int? = null,

    @field:SerializedName("content")
    val content: List<T>? = null,

    @field:SerializedName("number")
    val number: Int? = null,

    @field:SerializedName("sort")
    val sort: List<Sort>? = null,

    @field:SerializedName("first")
    val first: Boolean? = null,

    @field:SerializedName("last")
    val last: Boolean? = null,

    @field:SerializedName("empty")
    val empty: Boolean? = null
)

data class Pageable(
    @field:SerializedName("unpaged")
    val unpaged: Boolean? = null,

    @field:SerializedName("pageNumber")
    val pageNumber: Int? = null,

    @field:SerializedName("paged")
    val paged: Boolean? = null,

    @field:SerializedName("pageSize")
    val pageSize: Int? = null,

    @field:SerializedName("offset")
    val offset: Long? = null,

    @field:SerializedName("sort")
    val sort: List<Sort>? = null
)

data class Sort(
    @field:SerializedName("direction")
    val direction: String? = null,

    @field:SerializedName("nullHandling")
    val nullHandling: String? = null,

    @field:SerializedName("ascending")
    val ascending: Boolean? = null,

    @field:SerializedName("property")
    val property: String? = null,

    @field:SerializedName("ignoreCase")
    val ignoreCase: Boolean? = null
)