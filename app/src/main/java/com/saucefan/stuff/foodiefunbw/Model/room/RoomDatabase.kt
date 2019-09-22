package com.saucefan.stuff.foodiefunbw.Model.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.saucefan.stuff.foodiefunbw.Model.FoodieEntry

@Database(entities = [FoodieEntry::class], version = 2, exportSchema = false)
abstract class EntryDatabase : RoomDatabase() {
    abstract fun RoomDao(): RoomDao
}