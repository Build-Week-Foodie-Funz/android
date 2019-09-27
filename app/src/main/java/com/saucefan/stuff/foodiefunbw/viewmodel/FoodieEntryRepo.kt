package com.saucefan.stuff.foodiefunbw.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.room.Query
import com.saucefan.stuff.foodiefunbw.Model.FoodieEntry
import com.saucefan.stuff.foodiefunbw.Model.FoodieRestaurant
import com.saucefan.stuff.foodiefunbw.Model.room.RoomDao
import kotlinx.coroutines.launch

/*
*
* since we have a one-to-many relationship between restaurants and reviews,
* and every review should have a restaurant parent, I anticipate there will be some issues
* with changing restaurant names, deleting restaurants and then leaving behind orphaned reviews,
* etc -- however, we will simply have to minimize these issues as they present themselves
* in the future, the next time we have to model this style of relationship. We would be well served by having
* additional knowledge of sqlite, foriegn keys and best practices in one-to-many situations.
*
* for now I think we can plow through.
*
* */


class FoodieEntryRepo (private val roomDao:RoomDao) {




    val allEntries: LiveData<List<FoodieEntry>> = roomDao.returnAllReviewss()
    val allRestaurants:LiveData<List<FoodieRestaurant>> = roomDao.returnAllRestaurants()


    suspend fun insertReview(foodieEntry: FoodieEntry) {
        roomDao.insertReview(foodieEntry)
    }
    suspend fun insertRestaurant(foodieRestaurant: FoodieRestaurant) {
        roomDao.insertRestaurant(foodieRestaurant)
    }

    suspend fun updateReview(foodieEntry: FoodieEntry){
        roomDao.updateReview(foodieEntry)

    }
    suspend fun updateRestaurant(foodieRestaurant: FoodieRestaurant){
        roomDao.updateRestaurant(foodieRestaurant)
    }


    suspend fun deleteReview(foodieEntry: FoodieEntry){
        roomDao.deleteReview(foodieEntry)
    }

    suspend fun deleteRestaurant(foodieRestaurant: FoodieRestaurant){
        roomDao.deleteRestaurant(foodieRestaurant)
    }

    fun returnAllReviews():LiveData<List<FoodieEntry>>{
    return roomDao.returnAllReviewss()
}
    fun returnAllRestaurants(): LiveData<List<FoodieRestaurant>> {
        return roomDao.returnAllRestaurants()
    }

    fun returnReviewByRestName(name:String): LiveData<List<FoodieEntry>> {
        return roomDao.returnReviewByRestName(name)
    }
    suspend fun getEntriesByDate(time:Long):List<FoodieRestaurant>{
            return roomDao.getEntriesByDate(time)
            }
    suspend fun getRestByID(id:Int): FoodieRestaurant {
        return roomDao.getRestByID(id)
    }

    suspend fun getReviewsByID(id:Int): FoodieEntry {
         return roomDao.getReviewsByID(id)
     }

}


/*
    fun insertRestaurant(foodieRestaurant: FoodieRestaurant) =
            viewModelScope.launch {
                repository.insertRestaurant(foodieRestaurant)
            }
    fun updateReview(foodieEntry: FoodieEntry) = viewModelScope.launch {
        repository.updateReview(foodieEntry)
    }
    fun updateReview(foodieRestaurant: FoodieRestaurant) = viewModelScope.launch {
        repository.updateRestaurant(foodieRestaurant)
    }

    fun deleteReview(foodieEntry: FoodieEntry) = viewModelScope.launch {
        repository.deleteReview(foodieEntry)
    }

    fun deleteRestaurant(foodieRestaurant: FoodieRestaurant) = viewModelScope.launch {
        repository.deleteResaurant(foodieRestaurant)
    }

    fun returnAllReviews(): LiveData<List<FoodieEntry>> {
        return repository.returnAllReviews()
    }
    fun returnAllRestaurants(): LiveData<List<FoodieRestaurant>> {
        return repository.returnAllRestaurants()
    }

    fun returnReviewByRestName(name:String): LiveData<List<FoodieEntry>> {
        return repository.returnReviewByRestName()
    }

    */


