package com.saucefan.stuff.foodiefunbw.viewmodel

import android.content.Context
import androidx.core.graphics.rotationMatrix
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.saucefan.stuff.foodiefunbw.Model.FoodieEntry
import com.saucefan.stuff.foodiefunbw.Model.room.EntryDatabase
import com.saucefan.stuff.foodiefunbw.Model.room.RoomDao
import kotlinx.coroutines.*

class FoodieEntryRepo (context: Context) {
 private var roomDao:RoomDao
    init {
        val database: EntryDatabase = EntryDatabase.getInstance(
                context
        )!!

        roomDao = database.RoomDao()
    }


    suspend fun insertItem(foodieEntry: FoodieEntry) {
            roomDao.insert(foodieEntry)
    }
    suspend fun updateItem(foodieEntry: FoodieEntry){
        roomDao.update(foodieEntry)
    }
    suspend fun returnAllItems():List<FoodieEntry>{
         return roomDao.readAllEntries()
    }
    suspend fun deleteItem(foodieEntry: FoodieEntry){
        roomDao.delete(foodieEntry)
    }
    suspend fun getEntriesByDate(time:Long):List<FoodieEntry>{
            return roomDao.getEntriesByDate(time)
            }

}