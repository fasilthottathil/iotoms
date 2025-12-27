package com.iotoms.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.iotoms.data.local.entity.VenueEntity

/**
 * Created by Fasil on 26/12/2025
 */
@Dao
interface VenueDao {
    @Upsert
    suspend fun insertVenues(venues: List<VenueEntity>)

    @Query("SELECT * FROM venues")
    suspend fun getAllVenues(): List<VenueEntity>

    @Query("DELETE FROM venues")
    suspend fun deleteAllVenues()

    @Query("SELECT * FROM venues WHERE id = :id")
    suspend fun getVenueById(id: Int): VenueEntity?

    @Query("DELETE FROM venues WHERE id = :id")
    suspend fun deleteVenueById(id: Int)
}