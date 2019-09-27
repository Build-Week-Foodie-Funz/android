package com.saucefan.stuff.foodiefunbw.Model.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.saucefan.stuff.foodiefunbw.Model.FoodieEntry
import com.saucefan.stuff.foodiefunbw.Model.FoodieRestaurant

@Dao
interface RoomDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReview(foodieEntry: FoodieEntry)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRestaurant(foodieRestaurant: FoodieRestaurant)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateReview(foodieEntry: FoodieEntry)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateRestaurant(foodieRestaurant: FoodieRestaurant)

    @Delete
    suspend fun deleteReview(foodieEntry: FoodieEntry)

    @Delete
    suspend fun deleteRestaurant(foodieRestaurant: FoodieRestaurant)

    @Query("SELECT * FROM review_table ORDER by revId desc")
    fun returnAllReviewss(): LiveData<List<FoodieEntry>>

    @Query("SELECT * FROM restaurant_table ORDER by rest_id desc")
    fun returnAllRestaurants(): LiveData<List<FoodieRestaurant>>

    @Query("SELECT * FROM review_table where rest_name like :name")
    fun returnReviewByRestName(name:String): LiveData<List<FoodieEntry>>

    @Query("SELECT * FROM restaurant_table where recent_visit like :time")
    suspend fun getEntriesByDate(time:Long): List<FoodieRestaurant>

    @Query("SELECT * FROM restaurant_table where rest_name like :restName ")
    fun getRestName(restName: String): LiveData<List<FoodieRestaurant>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun init(foodieEntry: FoodieEntry)

    @Query("SELECT * FROM restaurant_table where restId like :id")
    suspend fun getRestByID(id:Int): FoodieRestaurant

    @Query("SELECT * FROM review_table where revId like :id")
    suspend fun getReviewsByID(id:Int): FoodieEntry

    @Query("SELECT * FROM restaurant_table")
    fun getAllRest(): LiveData<List<FoodieRestaurant>>


}