package com.saucefan.stuff.foodiefunbw.Model.room

import android.app.Application
import android.content.Context
import android.os.AsyncTask
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.saucefan.stuff.foodiefunbw.Model.Converters
import com.saucefan.stuff.foodiefunbw.Model.FoodieEntry
import com.saucefan.stuff.foodiefunbw.viewmodel.FoodieEntryViewModel

@Database(entities = [FoodieEntry::class], version = 1, exportSchema = true)
@TypeConverters(Converters::class)
abstract class EntryDatabase : RoomDatabase() {
    abstract fun RoomDao(): RoomDao

    companion object {
        private var instance: EntryDatabase? = null

        fun getInstance(context: Context): EntryDatabase? {
            if (instance == null) {
                synchronized(EntryDatabase::class) {
                    instance = Room.databaseBuilder(
                            context.applicationContext,
                            EntryDatabase::class.java, "entry_database"
                    )
                            .fallbackToDestructiveMigration() // when version increments, it migrates (deletes db and creates new) - else it crashes
                            .addCallback(roomCallback)
                            .build()
                }
            }
            return instance as EntryDatabase
        }

        fun destroyInstance() {
            instance = null
        }

        private val roomCallback = object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                PopulateDbAsyncTask(instance)
                        .execute()
            }
        }
    }

    suspend fun addEntries() {
        database.withTransaction {
            EntryMockData.entryList.forEach {
                RoomDao()?.insert(it)
            }
        }
    }
    addEntries()
    //Mock Data here
    class PopulateDbAsyncTask(db: EntryDatabase?) : AsyncTask<Unit, Unit, Unit>() {
        private val RoomDao = db?.RoomDao()

        override fun doInBackground(vararg p0: Unit?) {


        }
    }
}



}


