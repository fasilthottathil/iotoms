package com.iotoms.data.mapper

import com.iotoms.data.local.entity.QuickPickEntity
import com.iotoms.data.model.response.QuickPickPagesItem

/**
 * Created by Fasil on 28/12/2025
 */
fun QuickPickPagesItem.toQuickPickEntity(): QuickPickEntity {
    return QuickPickEntity(
        id = this.id.orEmpty(),
        backgroundColor = this.backgroundColor,
        buttons = this.buttons,
        pageIndex = this.pageIndex,
        title = this.title
    )
}