package com.saucefan.stuff.foodiefunbw.Model.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.saucefan.stuff.foodiefunbw.Model.FoodieEntry

@Dao
interface RoomDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(foodieEntry: FoodieEntry)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(foodieEntry: FoodieEntry)

    @Delete
    suspend fun delete(foodieEntry: FoodieEntry)
    @Query("SELECT * FROM foodieentry")
    suspend fun readAllEntries(): List<FoodieEntry>

}