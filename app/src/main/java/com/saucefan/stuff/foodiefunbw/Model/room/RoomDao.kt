package com.saucefan.stuff.foodiefunbw.Model.room

import androidx.room.*
import com.saucefan.stuff.foodiefunbw.Model.FoodieEntry

@Dao
interface RoomDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insert(foodieEntry: FoodieEntry)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(foodieEntry: FoodieEntry)

    @Delete
    fun delete(foodieEntry: FoodieEntry)
}