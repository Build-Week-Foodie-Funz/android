package com.saucefan.stuff.foodiefunbw.Model.room

import android.content.Context
import android.os.AsyncTask
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteDatabase
import com.saucefan.stuff.foodiefunbw.Model.Converters
import com.saucefan.stuff.foodiefunbw.Model.FoodieEntry
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Database(entities = [FoodieEntry::class], version = 2, exportSchema = true)
@TypeConverters(Converters::class)
abstract class EntryDatabase : RoomDatabase() {
    abstract fun RoomDao(): RoomDao



    private class EntryDatabaseCallback(
            private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch {
                    var roomDao = database.RoomDao()
                    EntryMockData.entryList.forEach {
                        roomDao.insert(it)
                    }
                }
            }
        }
    }


    companion object {
        @Volatile
        private var INSTANCE: EntryDatabase? = null

        fun getInstance(context: Context,scope: CoroutineScope): EntryDatabase? {
        val tempInstance = INSTANCE
        if (tempInstance != null) {
            return tempInstance
        }
            return INSTANCE ?: synchronized(this) {
                   val instance = Room.databaseBuilder(
                            context.applicationContext,
                            EntryDatabase::class.java, "entry_database")
                           .fallbackToDestructiveMigration() // when version increments, it migrates (deletes db and creates new) - else it crashes
                           .addCallback(EntryDatabaseCallback(scope))
                            .build()
                    INSTANCE=instance
                    return instance

                }
            }


        fun destroyInstance() {
            INSTANCE = null
        }


        // so the hope

        private val roomCallback = object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                PopulateDbAsyncTask(INSTANCE)
                        .execute()
            }
        }
    }

    //Mock Data here
    class PopulateDbAsyncTask(db: EntryDatabase?) : AsyncTask<Unit, Unit, Unit>() {
        private val roomDao = db?.RoomDao()

        override fun doInBackground(vararg p0: Unit?) {

        }

    }
}

/*
      private val roomCallback = object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)

                GlobalScope.launch {
                    val roomDao = instance?.RoomDao()
                    EntryMockData.entryList.forEach() {
                            roomDao?.insert(it)
                    }
                }
            }
        }
*/













