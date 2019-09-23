package com.saucefan.stuff.foodiefunbw.Model.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.saucefan.stuff.foodiefunbw.Model.FoodieEntry

@Dao
interface RoomDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(foodieEntry: FoodieEntry)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(foodieEntry: FoodieEntry)

    @Delete
    suspend fun delete(foodieEntry: FoodieEntry)
    @Query("SELECT * FROM Foodie_Table")
    suspend fun readAllEntries(): List<FoodieEntry>

    @Query("SELECT * FROM Foodie_Table where entryTime like :time")
    suspend fun getEntriesByDate(time:Long): List<FoodieEntry>


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun init(foodieEntry: FoodieEntry)
}