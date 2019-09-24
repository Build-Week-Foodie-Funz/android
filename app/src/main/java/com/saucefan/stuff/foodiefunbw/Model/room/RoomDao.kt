package com.saucefan.stuff.foodiefunbw.Model.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.saucefan.stuff.foodiefunbw.Model.FoodieEntry
import com.saucefan.stuff.foodiefunbw.Model.FoodieRestaurant

@Dao
interface RoomDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReview(foodieEntry: FoodieEntry)

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertRest(foodieRestaurant: FoodieRestaurant)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(foodieEntry: FoodieEntry)

    @Delete
    suspend fun delete(foodieEntry: FoodieEntry)
    @Query("SELECT * FROM review_table ORDER by revId desc")
    fun readAllEntries(): LiveData<List<FoodieEntry>>

    @Query("SELECT * FROM restaurant_table where recent_visit like :time")
    suspend fun getEntriesByDate(time:Long): List<FoodieRestaurant>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun init(foodieEntry: FoodieEntry)

}