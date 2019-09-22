package com.saucefan.stuff.foodiefunbw.Model.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.saucefan.stuff.foodiefunbw.Model.Converters
import com.saucefan.stuff.foodiefunbw.Model.FoodieEntry

@Database(entities = [FoodieEntry::class], version = 2, exportSchema = false)
@TypeConverters(Converters::class)
abstract class EntryDatabase : RoomDatabase() {
    abstract fun RoomDao(): RoomDao
}


