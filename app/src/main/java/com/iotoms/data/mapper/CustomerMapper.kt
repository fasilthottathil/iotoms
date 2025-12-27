package com.iotoms.data.mapper

import com.iotoms.data.local.entity.CustomerEntity
import com.iotoms.data.model.response.CustomerResponse

/**
 * Created by Fasil on 26/12/2025
 */
fun CustomerResponse.toCustomerEntity(): CustomerEntity {
    return CustomerEntity(
        customerNumber = customerNumber.orEmpty(),
        lastName = lastName,
        country = country,
        zipCode = zipCode,
        modifiedTime = modifiedTime,
        city = city,
        mobileNumber = mobileNumber,
        firstName = firstName,
        landPhoneNumber = landPhoneNumber,
        countryCode = countryCode,
        dob = dob,
        createdTime = createdTime,
        id = id,
        state = state,
        email = email,
        status = status
    )
}