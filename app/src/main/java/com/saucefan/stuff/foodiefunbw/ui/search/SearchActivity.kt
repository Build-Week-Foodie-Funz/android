package com.saucefan.stuff.foodiefunbw.ui.search

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.room.RoomDatabase
import com.saucefan.stuff.foodiefunbw.FoodieFun
import com.saucefan.stuff.foodiefunbw.Model.FoodieEntry
import com.saucefan.stuff.foodiefunbw.Model.room.EntryMockData
import com.saucefan.stuff.foodiefunbw.Model.room.RoomDao
import com.saucefan.stuff.foodiefunbw.R
import com.saucefan.stuff.foodiefunbw.viewmodel.FoodieEntryViewModel
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class SearchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)


        /// this is test code, to be deleted
        var foodieViewModel = ViewModelProviders.of(this).get(FoodieEntryViewModel::class.java)
        var tempCollect = mutableListOf<FoodieEntry>()
        btn.setOnClickListener {
            GlobalScope.launch {
                val collections = foodieViewModel.returnAllItems()
                for (i in 0 until collections.size) {
                    tempCollect.add(collections[i])
                }
            }


        }

    }
}