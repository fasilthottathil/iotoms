package com.iotoms.data.mapper

import com.iotoms.data.local.entity.UserEntity
import com.iotoms.data.model.response.UserDataItem
import com.iotoms.utils.extensions.getOrZero

/**
 * Created by Fasil on 25/12/2025
 */
fun UserDataItem.toUserEntity() = UserEntity(
    userId = this.userId.getOrZero(),
    firstName = this.firstName.orEmpty(),
    lastName = this.lastName.orEmpty(),
    createdAt = this.createdAt.orEmpty(),
    roleId = this.roleId.getOrZero(),
    enablePin = this.enablePin ?: false,
    passwordUpdatedOn = this.passwordUpdatedOn.orEmpty(),
    email = this.email.orEmpty(),
    username = this.username.orEmpty(),
    updatedAt = this.updatedAt.orEmpty()
)
