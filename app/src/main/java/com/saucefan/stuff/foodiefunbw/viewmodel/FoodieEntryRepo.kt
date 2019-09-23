package com.saucefan.stuff.foodiefunbw.viewmodel

import android.content.Context
import androidx.core.graphics.rotationMatrix
import androidx.room.Room
import com.saucefan.stuff.foodiefunbw.Model.FoodieEntry
import com.saucefan.stuff.foodiefunbw.Model.room.EntryDatabase
import com.saucefan.stuff.foodiefunbw.Model.room.RoomDao
import kotlinx.coroutines.runBlocking

class FoodieEntryRepo (context: Context) {
 private var roomDao:RoomDao
    init {
        val database: EntryDatabase = EntryDatabase.getInstance(
                context
        )!!

        roomDao = database.RoomDao()
    }


    fun insertItem(foodieEntry: FoodieEntry) {
        runBlocking {
            roomDao.insert(foodieEntry)
        }


    }

}