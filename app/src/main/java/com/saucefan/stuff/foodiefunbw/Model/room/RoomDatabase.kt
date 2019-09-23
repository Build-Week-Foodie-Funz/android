package com.saucefan.stuff.foodiefunbw.Model.room

import android.app.Application
import android.content.Context
import android.os.AsyncTask
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteDatabase
import com.saucefan.stuff.foodiefunbw.Model.Converters
import com.saucefan.stuff.foodiefunbw.Model.FoodieEntry
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.jetbrains.annotations.Async
import java.security.KeyStore


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


        fun main(instance: Database) {
            // this: CoroutineScope
            runBlocking {
                launch {

                    // launch a new coroutine in the scope of runBlocking
                    delay(1000L)
                    println("World!")
                }
                println("Hello,")
            }
        }
        suspend fun PopulateDbAsyncTask(db: EntryDatabase?) {
            val pokemonDao = db?.RoomDao()

           EntryMockData.entryList.forEach() {
                runBlocking {
                    pokemonDao?.insert(it)

                }
        }
    }
        private val roomCallback = object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)


            runBlocking {
                PopulateDbAsyncTask(instance)
            }
            }
        }
    }

    //Mock Data here
}













