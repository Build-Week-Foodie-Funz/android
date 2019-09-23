package com.saucefan.stuff.foodiefunbw.ui.search

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.saucefan.stuff.foodiefunbw.FoodieFun
import com.saucefan.stuff.foodiefunbw.Model.room.RoomDao
import com.saucefan.stuff.foodiefunbw.R
import com.saucefan.stuff.foodiefunbw.viewmodel.FoodieEntryViewModel

class SearchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        var foodieViewModel = ViewModelProviders.of(this).get(FoodieEntryViewModel::class.java)

        fun insertAthing(foodieFun: FoodieFun){


        }


    }
}
