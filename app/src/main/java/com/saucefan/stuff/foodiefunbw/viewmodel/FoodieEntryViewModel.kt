package com.saucefan.stuff.foodiefunbw.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.room.Delete
import com.saucefan.stuff.foodiefunbw.Model.FoodieEntry
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/*
*
*         Viewmodel
*    is the go between of
*           apps
*             &
*           fragments
*           and the
*               DB
*           sync serivce
*
*       afaict at this point we should be able to get away with this...
*       we could build a seperate repo but I feel this is already too much architecture for a simple app
*
*
* */

//View model should:
//be able to provide
//Lists<FoodieEntries> from the db such that
//by date
//most recent x posts
//whatever else
//
//

//should be able to dispatch a create event to the database
//should be able to tell the sync service to update certain files, delete certain files, restore if db is blank


class FoodieEntryViewModel(application: Application) : AndroidViewModel(application) {

    private var repository: FoodieEntryRepo =
            FoodieEntryRepo(application)

    suspend fun insertItem(foodieEntry: FoodieEntry) {
        repository.insertItem(foodieEntry)

    }
    suspend fun update(foodieEntry: FoodieEntry) {
         repository.updateItem(foodieEntry)
    }

    suspend fun delete(foodieEntry: FoodieEntry) {
        repository.deleteItem(foodieEntry)
    }
    suspend fun returnAllItems(): List<FoodieEntry> {
        return repository.returnAllItems()
    }
}