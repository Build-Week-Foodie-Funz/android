package com.saucefan.stuff.foodiefunbw.ui.search

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import androidx.room.RoomDatabase
import com.saucefan.stuff.foodiefunbw.FoodieFun
import com.saucefan.stuff.foodiefunbw.Model.FoodieEntry
import com.saucefan.stuff.foodiefunbw.Model.room.EntryMockData
import com.saucefan.stuff.foodiefunbw.Model.room.RoomDao
import com.saucefan.stuff.foodiefunbw.R
import com.saucefan.stuff.foodiefunbw.viewmodel.FoodieEntryViewModel

class SearchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)


        /// this is test code, to be deleted
        var foodieViewModel = ViewModelProviders.of(this).get(FoodieEntryViewModel::class.java)

        fun insertAthing(foodieFun: FoodieFun){

        }
            foodieViewModel.insertItem(FoodieEntry(
                    1,
                    "1",
                    1,
                    "sauce",
                    "oh we sauceyboys",
                    3,
                    "  something else ",
                    EntryMockData.stupidList,
                    false,
                    false))

    }
}
