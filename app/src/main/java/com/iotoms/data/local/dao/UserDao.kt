package com.iotoms.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.iotoms.data.local.entity.UserEntity

/**
 * Created by Fasil on 25/12/2025
 */
@Dao
interface UserDao {
    @Upsert
    suspend fun insertUsers(userEntities: List<UserEntity>)

    @Query("SELECT * FROM users")
    suspend fun getAllUsers(): List<UserEntity>

    @Query("SELECT * FROM users WHERE userId = :userId LIMIT 1")
    suspend fun getUserById(userId: Int): UserEntity?

    @Query("DELETE FROM users")
    suspend fun clearUsers()

    @Query("DELETE FROM users WHERE userId = :userId")
    suspend fun deleteUserById(userId: Int)
}