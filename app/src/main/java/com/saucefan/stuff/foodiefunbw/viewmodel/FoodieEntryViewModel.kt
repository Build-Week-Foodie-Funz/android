package com.saucefan.stuff.foodiefunbw.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.saucefan.stuff.foodiefunbw.Model.FoodieEntry
import com.saucefan.stuff.foodiefunbw.Model.FoodieRestaurant
import com.saucefan.stuff.foodiefunbw.Model.room.EntryDatabase
import com.saucefan.stuff.foodiefunbw.Model.room.RoomDao
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
//should be able to tell the sync service to updateReview certain files, deleteReview certain files, restore if db is blank


class FoodieEntryViewModel(application: Application) : AndroidViewModel(application) {
    // The ViewModel maintains a reference to the repository to get data.

    // LiveData gives us updated words when they change.
    private val repository: FoodieEntryRepo
    val allReviews: LiveData<List<FoodieEntry>>
    val allRestaurants:LiveData<List<FoodieRestaurant>>
    init {
        // Gets reference to WordDao from WordRoomDatabase to construct
        // the correct WordRepository.
        val roomDao = EntryDatabase.getInstance(application)?.RoomDao()
        repository  = FoodieEntryRepo(roomDao as RoomDao)
        allReviews = repository.allEntries
        allRestaurants =repository.allRestaurants
    }



   fun insertReview(foodieEntry: FoodieEntry) =
           viewModelScope.launch {
            repository.insertReview(foodieEntry)
    }

    fun insertRestaurant(foodieRestaurant: FoodieRestaurant) =
            viewModelScope.launch {
                repository.insertRestaurant(foodieRestaurant)
            }
   fun updateReview(foodieEntry: FoodieEntry) = viewModelScope.launch {
         repository.updateReview(foodieEntry)
    }
    fun updateRestaurant(foodieRestaurant: FoodieRestaurant) = viewModelScope.launch {

        //TODO THIS NEEDS TO DELETE ALL REVIEWS WITH THE SAME NAME AS THE PARENT, FIX THIS ASAP
        repository.updateRestaurant(foodieRestaurant)
    }

    fun deleteReview(foodieEntry: FoodieEntry) = viewModelScope.launch {
        repository.deleteReview(foodieEntry)
    }

    fun deleteRestaurant(foodieRestaurant: FoodieRestaurant) = viewModelScope.launch {
        repository.deleteRestaurant(foodieRestaurant)
    }

    fun returnAllReviews(): LiveData<List<FoodieEntry>> {
        return repository.returnAllReviews()
    }
    fun returnAllRestaurants(): LiveData<List<FoodieRestaurant>> {
        return repository.returnAllRestaurants()
    }

    fun returnReviewByRestName(name:String): LiveData<List<FoodieEntry>> {
        return repository.returnReviewByRestName(name)
    }
    suspend  fun getRestByID(id:Int): FoodieRestaurant {
        return repository.getRestByID(id)
    }

    suspend fun getReviewsByID(id:Int): FoodieEntry {
        return repository.getReviewsByID(id)
    }
}