package com.saucefan.stuff.foodiefunbw.viewmodel

import androidx.lifecycle.LiveData
import androidx.room.Query
import com.saucefan.stuff.foodiefunbw.Model.FoodieEntry
import com.saucefan.stuff.foodiefunbw.Model.FoodieRestaurant
import com.saucefan.stuff.foodiefunbw.Model.room.RoomDao

class FoodieEntryRepo (private val roomDao:RoomDao) {


    val allEntries: LiveData<List<FoodieEntry>> = roomDao.readAllEntries()


    suspend fun insertItem(foodieEntry: FoodieEntry) {
        roomDao.insertReview(foodieEntry)
    }
    suspend fun updateItem(foodieEntry: FoodieEntry){
        roomDao.update(foodieEntry)
    }
     fun returnAllItems():LiveData<List<FoodieEntry>>{
         return roomDao.readAllEntries()
    }
    suspend fun deleteItem(foodieEntry: FoodieEntry){
        roomDao.delete(foodieEntry)
    }
    suspend fun getEntriesByDate(time:Long):List<FoodieRestaurant>{
            return roomDao.getEntriesByDate(time)
            }
    suspend fun getRestByID(id:Int): FoodieRestaurant {
        return roomDao.getRestByID(id)
    }

    suspend fun getReviewsByID(id:Int): FoodieEntry {
         return roomDao.getReviewsByID(id)
     }

    fun returnAllRestaurants(): LiveData<List<FoodieRestaurant>> {
        return roomDao.getAllRest()
    }




}