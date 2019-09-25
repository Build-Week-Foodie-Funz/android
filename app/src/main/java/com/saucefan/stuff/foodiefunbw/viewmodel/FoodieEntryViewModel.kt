package com.saucefan.stuff.foodiefunbw.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.room.Delete
import androidx.room.Room
import androidx.room.RoomDatabase
import com.saucefan.stuff.foodiefunbw.Model.FoodieEntry
import com.saucefan.stuff.foodiefunbw.Model.FoodieRestaurant
import com.saucefan.stuff.foodiefunbw.Model.room.EntryDatabase
import com.saucefan.stuff.foodiefunbw.Model.room.RoomDao
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
    // The ViewModel maintains a reference to the repository to get data.

    // LiveData gives us updated words when they change.
    private val repository: FoodieEntryRepo
    val allEntrees: LiveData<List<FoodieEntry>>
    init {
        // Gets reference to WordDao from WordRoomDatabase to construct
        // the correct WordRepository.
        val roomDao = EntryDatabase.getInstance(application)?.RoomDao()
        repository  = FoodieEntryRepo(roomDao as RoomDao)
        allEntrees = repository.allEntries
    }



   fun insertItem(foodieEntry: FoodieEntry) =
           viewModelScope.launch {
            repository.insertItem(foodieEntry)
    }
   fun updateItem(foodieEntry: FoodieEntry) = viewModelScope.launch {
         repository.updateItem(foodieEntry)
    }

    fun deleteItem(foodieEntry: FoodieEntry) = viewModelScope.launch {
        repository.deleteItem(foodieEntry)
    }
    fun returnAllItems(): LiveData<List<FoodieEntry>> {
        return repository.returnAllItems()
    }
    suspend  fun getRestByID(id:Int): FoodieRestaurant {
        return repository.getRestByID(id)
    }

    suspend fun getReviewsByID(id:Int): FoodieEntry {
        return repository.getReviewsByID(id)
    }
}